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
import vn.edu.iuh.fit.backend.dtos.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.*;
import vn.edu.iuh.fit.backend.services.JobApplicationService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

            Candidate candidate;
            if (jobApplicationDTO.getCandidate().getId() != null) {
                Candidate existingCandidate = candidateRepository.findById(jobApplicationDTO.getCandidate().getId())
                        .orElseThrow(() -> new Exception("Candidate not found"));
                System.out.println("Candidate found");

                candidate = updateCandidate(jobApplicationDTO, existingCandidate);

            } else {
                candidate = candidateMapper.toEntity(jobApplicationDTO.getCandidate());

                // Save candidate's user
                if (jobApplicationDTO.getCandidate().getUserId() != null) {
                    User user = userRepository.findById(jobApplicationDTO.getCandidate().getUserId())
                            .orElse(null);

                    // If the user is not null, add the role "CANDIDATE" to the user
                    if (user != null && user.getRoles().stream().noneMatch(role -> role.getRoleName().equals("CANDIDATE"))) {
                        List<Role> roles = new ArrayList<>();
                        Role role = roleRepository.findByRoleName("CANDIDATE");
                        roles.add(role);
                        user.setRoles(roles);
                        userRepository.save(user);
                    }
                    candidate.setUser(user);
                }

                // Save candidate's address
                Address address = addressMapper.toEntity(jobApplicationDTO.getCandidate().getAddress());
                addressRepository.save(address);
                candidate.setAddress(address);

                candidateRepository.save(candidate);

                // Save candidate's experiences
                if (jobApplicationDTO.getCandidate().getExperiences() != null) {
                    List<Experience> experiences = new ArrayList<>();
                    jobApplicationDTO.getCandidate().getExperiences().forEach(ex -> {
                        Experience experience = experienceMapper.toEntity(ex);
                        experience.setCandidate(candidate);
                        experienceRepository.save(experience);
                        experiences.add(experience);
                    });
                    candidate.setExperiences(experiences);
                }

                // Save candidate's skills
                if (jobApplicationDTO.getCandidate().getCandidateSkills() != null) {
                    List<CandidateSkill> candidateSkills = new ArrayList<>();
                    jobApplicationDTO.getCandidate().getCandidateSkills().forEach(candidateSkillDTO -> {
                        CandidateSkill newCandidateSkill = candidateSkillMapper.toEntity(candidateSkillDTO);

                        Skill skill = skillRepository.save(skillMapper.toEntity(candidateSkillDTO.getSkill()));

                        saveCandidateSkill(candidate, candidateSkills, candidateSkillDTO, newCandidateSkill, skill);
                    });
                    candidate.setCandidateSkills(candidateSkills);
                }
            }
            jobApplication.setCandidate(candidate);
            System.out.println("Candidate saved" + candidate);
            double matchPercent = calculateMatchPercent(jobApplicationDTO, candidate);
            jobApplication.setMatchPercentage(matchPercent);
            return jobApplicationMapper.toDTO(jobApplicationRepository.save(jobApplication));
        } catch (Exception e) {
            return null;
        }
    }

    private Candidate updateCandidate(JobApplicationDTO jobApplicationDTO, Candidate candidate) {
        try {
            candidate.setFullName(jobApplicationDTO.getCandidate().getFullName());
            candidate.setDob(jobApplicationDTO.getCandidate().getDob());
            candidate.setEmail(jobApplicationDTO.getCandidate().getEmail());
            candidate.setPhone(jobApplicationDTO.getCandidate().getPhone());

            // Update candidate's address
            Address address = addressRepository.findById(jobApplicationDTO.getCandidate().getAddress().getId())
                    .orElseThrow(() -> new Exception("Address not found"));
            Address newAddress = addressMapper.toEntity(jobApplicationDTO.getCandidate().getAddress());
            address.setCity(newAddress.getCity());
            address.setCountry(newAddress.getCountry());
            address.setNumber(newAddress.getNumber());
            address.setZipcode(newAddress.getZipcode());
            address.setStreet(newAddress.getStreet());
            addressRepository.save(address);
            candidate.setAddress(address);

            // Update candidate's experiences
            List<Experience> experiences = new ArrayList<>();
            jobApplicationDTO.getCandidate().getExperiences().forEach(ex -> {
                if (ex.getId() != null) {
                    try {
                        Experience experience = experienceRepository.findById(ex.getId())
                                .orElseThrow(() -> new Exception("Experience not found"));
                        experience.setRole(ex.getRole());
                        experience.setCompanyName(ex.getCompanyName());
                        experience.setFromDate(ex.getFromDate());
                        experience.setToDate(ex.getToDate());
                        experience.setWorkDescription(ex.getWorkDescription());
                        experienceRepository.save(experience);
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

            // Update candidate's skills
            if (jobApplicationDTO.getCandidate().getCandidateSkills() != null) {
                List<CandidateSkill> candidateSkills = new ArrayList<>();
                jobApplicationDTO.getCandidate().getCandidateSkills().forEach(candidateSkillDTO -> {
                    CandidateSkill newCandidateSkill = candidateSkillMapper.toEntity(candidateSkillDTO);

                    Skill skill;
                    if (candidateSkillDTO.getSkill().getId() != null) {
                        try {
                            skill = skillRepository.findById(candidateSkillDTO.getSkill().getId())
                                    .orElseThrow(() -> new Exception("Skill not found"));
                            skill.setType(candidateSkillDTO.getSkill().getType());
                            skill.setSkillName(candidateSkillDTO.getSkill().getSkillName());
                            skillRepository.save(skill);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        skill = skillRepository.save(skillMapper.toEntity(candidateSkillDTO.getSkill()));
                    }

                    if (candidateSkillDTO.getId() != null) {
                        try {
                            CandidateSkill candidateSkill = candidateSkillRepository.findByCandidate_IdAndSkill_Id(candidate.getId(), skill.getId());
                            candidateSkill.setSkill(skill);
                            candidateSkill.setCandidate(candidate);
                            candidateSkill.setSkillLevel(candidateSkillDTO.getSkillLevel());
                            candidateSkillRepository.save(candidateSkill);
//                            candidateSkills.add(candidateSkill);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        saveCandidateSkill(candidate, candidateSkills, candidateSkillDTO, newCandidateSkill, skill);
                    }

                    candidateSkills.add(newCandidateSkill);

                });
                candidate.setCandidateSkills(candidateSkills);
            }
            candidateRepository.save(candidate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return candidate;
    }

    // Calculate match percent between job and candidate based on skills and applied date skill
    private double calculateMatchPercent(JobApplicationDTO jobApplicationDTO, Candidate candidate) {
        List<Skill> jobSkills = jobApplicationDTO.getJob().getJobSkills().stream()
                .map(skillDTO -> skillRepository.findById(skillDTO.getId().getSkillId()).orElse(null))
                .filter(Objects::nonNull)
                .toList();

        List<Skill> candidateSkills = candidate.getCandidateSkills().stream()
                .filter(cs -> cs.getAppliedDate().equals(jobApplicationDTO.getApplyAt()))
                .map(CandidateSkill::getSkill)
                .toList();

        if (jobSkills.isEmpty()) return 0.0;

        double matchScore = jobSkills.stream()
                .mapToDouble(jobSkill -> {
                    Optional<CandidateSkill> matchedSkill = candidate.getCandidateSkills().stream()
                            .filter(cs -> cs.getAppliedDate().equals(jobApplicationDTO.getApplyAt()) &&
                                    cs.getSkill().getSkillName().equalsIgnoreCase(jobSkill.getSkillName()))
                            .findFirst();

                    if (matchedSkill.isPresent()) {
                        SkillLevel jobLevel = jobApplicationDTO.getJob().getJobSkills().stream()
                                .filter(sd -> sd.getId().getSkillId().equals(jobSkill.getId()))
                                .findFirst()
                                .map(JobSkillDTO::getSkillLevel)
                                .orElse(null);

                        System.out.println("Matched skill: " + matchedSkill.get().getSkill().getSkillName());

                        return jobLevel != null && jobLevel.ordinal() <= matchedSkill.get().getSkillLevel().ordinal() ? 1.0 : 0.5;
                    }
                    System.out.println("No matched skill" + jobSkill.getSkillName());
                    return 0.0;
                })
                .sum();

        return (matchScore / jobSkills.size()) * 100;
    }

    private void saveCandidateSkill(Candidate candidate, List<CandidateSkill> candidateSkills, CandidateSkillDTO candidateSkillDTO, CandidateSkill newCandidateSkill, Skill skill) {
        CandidateSkillId candidateSkillId = new CandidateSkillId();
        candidateSkillId.setCandidateId(candidate.getId());
        candidateSkillId.setSkillId(skill.getId());

        newCandidateSkill.setId(candidateSkillId);
        newCandidateSkill.setSkill(skill);
        newCandidateSkill.setAppliedDate(LocalDate.now());
        newCandidateSkill.setSkillLevel(candidateSkillDTO.getSkillLevel());

        newCandidateSkill.setCandidate(candidate);
        candidateSkillRepository.save(newCandidateSkill);
        candidateSkills.add(newCandidateSkill);
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

    @Override
    public PageResponseDTO<JobApplicationDTO> getCandidates(Long companyId, Long jobId, String search, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        if (jobId != null && !search.isEmpty()) {
            return new PageResponseDTO<>(jobApplicationRepository.findByJob_IdAndCandidate_FullNameContaining(jobId, search, pageable).map(jobApplicationMapper::toDTO));
        } else if (jobId != null) {
            return new PageResponseDTO<>(jobApplicationRepository.findByJobId(jobId, pageable).map(jobApplicationMapper::toDTO));
        } else if (!search.isEmpty()) {
            return new PageResponseDTO<>(jobApplicationRepository.findByJob_CompanyIdAndCandidate_FullNameContaining(companyId, search, pageable).map(jobApplicationMapper::toDTO));
        } else {
            return new PageResponseDTO<>(jobApplicationRepository.findByJob_CompanyId(companyId, pageable).map(jobApplicationMapper::toDTO));
        }

    }
}
