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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.converters.JobMapper;
import vn.edu.iuh.fit.backend.converters.JobSkillMapper;
import vn.edu.iuh.fit.backend.dtos.JobDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;
import vn.edu.iuh.fit.backend.services.CompanyService;
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

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, JobSkillRepository jobSkillRepository, JobSkillMapper jobSkillMapper, CandidateRepository candidateRepository, CompanyRepository companyRepository, SkillRepository skillRepository) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.jobSkillRepository = jobSkillRepository;
        this.jobSkillMapper = jobSkillMapper;
        this.candidateRepository = candidateRepository;
        this.companyRepository = companyRepository;
        this.skillRepository = skillRepository;
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
    public PageResponseDTO<JobDTO> findMatchingJobsForCandidate(Long candidateId, long minSkills, Pageable pageable) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        // Get all skill ids of the candidate
        List<Long> skillIds = candidate.getCandidateSkills().stream()
                .map(cs -> cs.getSkill().getId())
                .toList();

        Page<JobDTO> matchingJobs = jobRepository.findJobBySkills(skillIds, minSkills, pageable)
                .map(jobMapper::toDTO);

        return new PageResponseDTO<>(matchingJobs);

    }
}
