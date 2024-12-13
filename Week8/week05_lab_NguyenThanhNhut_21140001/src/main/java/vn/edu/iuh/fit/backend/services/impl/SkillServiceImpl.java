/*
 * @ (#) SkillServiceImpl.java       1.0     13/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 13/11/2024
 * @version:    1.0
 */

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.converters.SkillMapper;
import vn.edu.iuh.fit.backend.dtos.SkillDTO;
import vn.edu.iuh.fit.backend.models.Skill;
import vn.edu.iuh.fit.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.backend.services.SkillService;

import java.util.HashSet;
import java.util.Set;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public Set<SkillDTO> getAllSkills() {
        Set<SkillDTO> skillDTOs = new HashSet<>();
        skillRepository.findAll().forEach(skill -> skillDTOs.add(skillMapper.toDTO(skill)));
        return skillDTOs;
    }

    @Override
    public SkillDTO save(SkillDTO skillDTO) {
        Skill skill = skillMapper.toEntity(skillDTO);
        return skillMapper.toDTO(skillRepository.save(skill));
    }

    @Override
    public SkillDTO getSkillById(Long id) {
        return skillMapper.toDTO(skillRepository.findById(id).orElse(null));
    }
}
