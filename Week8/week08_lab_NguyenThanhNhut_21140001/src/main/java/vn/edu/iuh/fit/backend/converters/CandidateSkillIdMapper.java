/*
 * @ (#) CandidateSkillIdMapper.java       1.0     07/11/2024
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
import vn.edu.iuh.fit.backend.dtos.CandidateSkillIdDTO;
import vn.edu.iuh.fit.backend.models.CandidateSkillId;

@Mapper(componentModel = "spring")
public interface CandidateSkillIdMapper {
    CandidateSkillIdMapper INSTANCE = Mappers.getMapper(CandidateSkillIdMapper.class);

    CandidateSkillIdDTO toDTO(CandidateSkillId candidateSkillId);

    CandidateSkillId toEntity(CandidateSkillIdDTO candidateSkillIdDTO);
}
