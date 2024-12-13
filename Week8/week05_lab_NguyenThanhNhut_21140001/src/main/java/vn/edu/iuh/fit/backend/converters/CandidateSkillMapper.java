/*
 * @ (#) CandidateSkillDTO.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.converters;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.iuh.fit.backend.dtos.CandidateSkillDTO;
import vn.edu.iuh.fit.backend.models.CandidateSkill;

@Mapper(componentModel = "spring")
public interface CandidateSkillMapper {
    CandidateSkillMapper INSTANCE = Mappers.getMapper(CandidateSkillMapper.class);

    CandidateSkillDTO toDTO(CandidateSkill candidateSkill);

    CandidateSkill toEntity(CandidateSkillDTO candidateSkillDTO);
}
