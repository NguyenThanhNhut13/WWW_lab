/*
 * @ (#) JobApplicationServiceImpl.java       1.0     17/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 17/11/2024
 * @version:    1.0
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.converters.*;
import vn.edu.iuh.fit.backend.dtos.JobApplicationDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;
import vn.edu.iuh.fit.backend.services.JobApplicationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobRepository jobRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper jobApplicationMapper;
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final CandidateSkillRepository candidateSkillRepository;
    private final CandidateSkillMapper candidateSkillMapper;
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;
    private final AddressRepository addressRepository;
    private final ExperienceRepository experienceRepository;
    private final AddressMapper addressMapper;
    private final ExperienceMapper experienceMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public JobApplicationServiceImpl(JobRepository jobRepository, JobApplicationRepository jobApplicationRepository, JobApplicationMapper jobApplicationMapper, CandidateRepository candidateRepository, CandidateMapper candidateMapper, CandidateSkillRepository candidateSkillRepository, CandidateSkillMapper candidateSkillMapper, SkillRepository skillRepository, SkillMapper skillMapper, AddressRepository addressRepository, ExperienceRepository experienceRepository, AddressMapper addressMapper, ExperienceMapper experienceMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.jobRepository = jobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobApplicationMapper = jobApplicationMapper;
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
        this.candidateSkillRepository = candidateSkillRepository;
        this.candidateSkillMapper = candidateSkillMapper;
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
        this.addressRepository = addressRepository;
        this.experienceRepository = experienceRepository;
        this.addressMapper = addressMapper;
        this.experienceMapper = experienceMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public JobApplicationDTO save(JobApplicationDTO jobApplicationDTO) {
        JobApplication jobApplication = jobApplicationMapper.toEntity(jobApplicationDTO);
        try {
            if (jobApplicationDTO.getJob().getId() != null) {
                Job job = jobRepository.findById(jobApplicationDTO.getJob().getId())
                        .orElseThrow(() -> new Exception("Job not found"));
                jobApplication.setJob(job);
            }
            if (jobApplicationDTO.getCandidate().getId() != null) {
                Candidate candidate = candidateRepository.findById(jobApplicationDTO.getCandidate().getId())
                        .orElseThrow(() -> new Exception("Candidate not found"));
                jobApplication.setCandidate(candidate);
            } else {
                Candidate candidate = candidateMapper.toEntity(jobApplicationDTO.getCandidate());

                // Save candidate's user
                if (jobApplicationDTO.getCandidate().getUserId() != null) {
                    User user = userRepository.findById(jobApplicationDTO.getCandidate().getUserId())
                            .orElse(null);

                    // If the user is not null, add the role "CANDIDATE" to the user
                    if (user != null) {
                        Set<Role> roles = new HashSet<>();
                        Role role = roleRepository.findByRoleName("CANDIDATE");
                        roles.add(role);
                        user.setRoles(roles);
                        userRepository.save(user);
                    }
                    candidate.setUser(user);
                }

                // Save candidate's address
                if (jobApplicationDTO.getCandidate().getAddress().getId() != null) {
                    Address address = addressRepository.findById(jobApplicationDTO.getCandidate().getAddress().getId())
                            .orElseThrow(() -> new Exception("Address not found"));
                    candidate.setAddress(address);
                } else {
                    Address address = addressMapper.toEntity(jobApplicationDTO.getCandidate().getAddress());
                    addressRepository.save(address);
                    candidate.setAddress(address);
                }

                candidateRepository.save(candidate);

                // Save candidate's experiences
                if (jobApplicationDTO.getCandidate().getExperiences() != null) {
                    List<Experience> experiences = new ArrayList<>();
                    jobApplicationDTO.getCandidate().getExperiences().forEach(ex -> {
                        if (ex.getId() != null) {
                            try {
                                Experience experience = experienceRepository.findById(ex.getId())
                                        .orElseThrow(() -> new Exception("Experience not found"));
                                experiences.add(experience);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            Experience experience = experienceMapper.toEntity(ex);
                            experience.setCandidate(candidate);
                            experienceRepository.save(experience);
                            experiences.add(experience);
                        }
                    });
                    candidate.setExperiences(experiences);
                }

                // Save candidate's skills
                if (jobApplicationDTO.getCandidate().getCandidateSkills() != null) {
                    List<CandidateSkill> candidateSkills = new ArrayList<>();
                    jobApplicationDTO.getCandidate().getCandidateSkills().forEach(candidateSkillDTO -> {
                        CandidateSkill newCandidateSkill = candidateSkillMapper.toEntity(candidateSkillDTO);

                        Skill skill = new Skill();
                        if (candidateSkillDTO.getSkill().getId() != null) {
                            try {
                                skill = skillRepository.findById(candidateSkillDTO.getSkill().getId())
                                        .orElseThrow(() -> new Exception("Skill not found"));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            skill = skillRepository.save(skillMapper.toEntity(candidateSkillDTO.getSkill()));
                        }
                        CandidateSkillId candidateSkillId = new CandidateSkillId();
                        candidateSkillId.setCanId(candidate.getId());
                        candidateSkillId.setSkillId(skill.getId());

                        newCandidateSkill.setId(candidateSkillId);
                        newCandidateSkill.setSkill(skill);
                        newCandidateSkill.setCan(candidate);
                        newCandidateSkill.setAppliedDate(LocalDate.now());

                        candidateSkillRepository.save(newCandidateSkill);

                        candidateSkills.add(newCandidateSkill);
                    });
                    candidate.setCandidateSkills(candidateSkills);
                }
                jobApplication.setCandidate(candidate);
            }
            return jobApplicationMapper.toDTO(jobApplicationRepository.save(jobApplication));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JobApplicationDTO findById(Long id) {
        return jobApplicationMapper.toDTO(jobApplicationRepository.findById(id).orElse(null));
    }

    @Override
    public PageResponseDTO<JobApplicationDTO> findByJobId(Long jobId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<JobApplicationDTO> jobApplications = jobApplicationRepository.findByJobId(jobId, pageable).map(jobApplicationMapper::toDTO);
        return new PageResponseDTO<>(jobApplications);
    }
}
