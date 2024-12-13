/*
 * @ (#) JobService.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import jakarta.transaction.Transactional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vn.edu.iuh.fit.backend.converters.JobMapper;
import vn.edu.iuh.fit.backend.converters.JobSkillMapper;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.dtos.JobRecommendationDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.*;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobSkillRepository jobSkillRepository;
    private final JobSkillMapper jobSkillMapper;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;
    private final SkillRepository skillRepository;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, JobSkillRepository jobSkillRepository, JobSkillMapper jobSkillMapper, CandidateRepository candidateRepository, CompanyRepository companyRepository, SkillRepository skillRepository, RestTemplate restTemplate, UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.jobSkillRepository = jobSkillRepository;
        this.jobSkillMapper = jobSkillMapper;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.skillRepository = skillRepository;
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public PageResponseDTO<JobDTO> getAllJob(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobDTO> jobs = jobRepository.findAll(pageable).map(jobMapper::toDTO);
        return new PageResponseDTO<>(jobs);
    }

    @Override
    public JobDTO getJobById(Long id) {
        return jobRepository.findById(id).map(jobMapper::toDTO).orElse(null);
    }

    @Override
    public PageResponseDTO<JobDTO> getJobsByCompanyId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobDTO> jobs = jobRepository.findJobsByCompany_Id(id, pageable).map(jobMapper::toDTO);
        return new PageResponseDTO<>(jobs);
    }

    @Override
    public JobDTO saveJob(JobDTO jobDTO) {
        Job job = jobMapper.toEntity(jobDTO);
        if (jobDTO.getCompany() != null && jobDTO.getCompany().getId() != null) {
            Company company = companyRepository.findById(jobDTO.getCompany().getId())
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            job.setCompany(company);
        }

        jobRepository.save(job);

        if (jobDTO.getJobSkills() != null) {
            List<JobSkill> jobSkills = new ArrayList<>();
            jobDTO.getJobSkills().forEach(jobSkillDTO -> {

                Skill skill = new Skill();
                // find skill by id
                if (jobSkillDTO.getSkill().getId() != null) {
                    skill = skillRepository.findById(jobSkillDTO.getSkill().getId())
                            .orElseThrow(() -> new RuntimeException("Skill not found"));

                } else {
                    // create new skill
                    skill.setSkillName(jobSkillDTO.getSkill().getSkillName());
                    skill.setSkillDescription(jobSkillDTO.getSkill().getDescription());
                    skill.setType(jobSkillDTO.getSkill().getType());
                    skillRepository.save(skill);
                }

                JobSkill jobSkill = jobSkillMapper.toEntity(jobSkillDTO);

                // create job skill id
                JobSkillId jobSkillId = new JobSkillId();
                jobSkillId.setJobId(job.getId());
                jobSkillId.setSkillId(skill.getId());

                jobSkill.setId(jobSkillId);
                jobSkill.setSkill(skill);
                jobSkill.setJob(job);

                jobSkillRepository.save(jobSkill);
                jobSkills.add(jobSkill);
            });
            job.setJobSkills(jobSkills);
        }
        return jobMapper.toDTO(jobRepository.save(job));
    }

    @Override
    public JobDTO updateJob(JobDTO jobDTO) {
        return saveJob(jobDTO);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public PageResponseDTO<JobDTO> searchJob(String keyword, int page, int size, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public PageResponseDTO<JobDTO> getRecommendJobs(String username, int page, int size) {
        Candidate candidate = candidateRepository.findByUser_Username(username);

        if (candidate == null) {
            List<Object[]> results = jobRepository.findPopularJobs(PageRequest.of(page, size));
            List<JobDTO> jobDTOS = new ArrayList<>();
            results.forEach(result -> {
                Long jobId = (Long) result[0];
                Job job = jobRepository.findById(jobId).orElse(null);
                if (job != null) {
                    jobDTOS.add(jobMapper.toDTO(job));
                }
            });
            return new PageResponseDTO<>(new PageImpl<>(jobDTOS, PageRequest.of(page, size), jobDTOS.size()));
        }

        String JOB_RECOMMENDATION_API_URL = "http://localhost:5000/recommendations/" + candidate.getId();

        ResponseEntity<Map<String, List<JobRecommendationDTO>>> response = restTemplate.exchange(
                JOB_RECOMMENDATION_API_URL,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Map<String, List<JobRecommendationDTO>>>() {}
        );

        List<JobRecommendationDTO> jobRecommendationDTOS = response.getBody().get("recommendations");

        List<JobDTO> jobs = new ArrayList<>();
        jobRecommendationDTOS.forEach(jobRecommendationDTO -> {
            Job job = jobRepository.findById(jobRecommendationDTO.getJob_id()).orElse(null);
            if (job != null && jobRecommendationDTO.getMatch_percentage() > 0) {
                JobDTO jobDTO = jobMapper.toDTO(job);
                jobDTO.setMatchPercentage(jobRecommendationDTO.getMatch_percentage());
                jobs.add(jobDTO);
            }
        });

        Page<JobDTO> jobPage = new PageImpl<>(jobs, PageRequest.of(page, size), jobs.size());

        return new PageResponseDTO<>(jobPage);
    }
}
