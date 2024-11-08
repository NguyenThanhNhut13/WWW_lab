/*
 * @ (#) JobSkillMapper.java       1.0     07/11/2024
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
import vn.edu.iuh.fit.backend.dtos.JobSkillDTO;
import vn.edu.iuh.fit.backend.models.JobSkill;

@Mapper(componentModel = "spring")
public interface JobSkillMapper {
    JobSkillMapper INSTANCE = Mappers.getMapper(JobSkillMapper.class);

    JobSkillDTO toDTO(JobSkill jobSkill);

    JobSkill toEntity(JobSkillDTO jobSkillDTO);
}
