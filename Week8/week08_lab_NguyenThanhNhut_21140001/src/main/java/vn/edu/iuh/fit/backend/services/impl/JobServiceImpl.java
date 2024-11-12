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
import vn.edu.iuh.fit.backend.models.Candidate;
import vn.edu.iuh.fit.backend.models.Job;
import vn.edu.iuh.fit.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.repositories.JobRepository;
import vn.edu.iuh.fit.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.backend.services.CompanyService;
import vn.edu.iuh.fit.backend.services.JobService;

import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobSkillRepository jobSkillRepository;
    private final JobSkillMapper jobSkillMapper;
    private final CandidateRepository candidateRepository;
    private final CompanyService companyService;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper, JobSkillRepository jobSkillRepository, JobSkillMapper jobSkillMapper, CandidateRepository candidateRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.jobSkillRepository = jobSkillRepository;
        this.jobSkillMapper = jobSkillMapper;
        this.candidateRepository = candidateRepository;
        this.companyService = companyService;
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
    public JobDTO saveJob(JobDTO jobDTO) {
        if (jobDTO.getCompany() != null && jobDTO.getCompany().getId() != null) {
            companyService.saveCompany(jobDTO.getCompany());
        }

        if (jobDTO.getJobSkills() != null) {
            jobDTO.getJobSkills().forEach(jobSkillDTO -> {
                jobSkillRepository.save(jobSkillMapper.toEntity(jobSkillDTO));
            });
        }

        return jobMapper.toDTO(jobRepository.save(jobMapper.toEntity(jobDTO)));
    }

    @Override
    public JobDTO updateJob(JobDTO jobDTO) {
        if (jobDTO.getCompany() != null && jobDTO.getCompany().getId() != null) {
            companyService.updateCompany(jobDTO.getCompany());
        }

        if (jobDTO.getJobSkills() != null) {
            jobDTO.getJobSkills().forEach(jobSkillDTO -> {
                jobSkillRepository.save(jobSkillMapper.toEntity(jobSkillDTO));
            });
        }

        return jobMapper.toDTO(jobRepository.save(jobMapper.toEntity(jobDTO)));
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
