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
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import vn.edu.iuh.fit.backend.dtos.JobSkillDTO;
import vn.edu.iuh.fit.backend.enums.SkillLevel;
import vn.edu.iuh.fit.backend.models.JobSkill;

@Mapper(componentModel = "spring")
public interface JobSkillMapper {
    JobSkillMapper INSTANCE = Mappers.getMapper(JobSkillMapper.class);

    @Mapping(target = "skillLevel", source = "skillLevel", qualifiedByName = "toSkillLevel")
    JobSkillDTO toDTO(JobSkill jobSkill);

    @Mapping(target = "skillLevel", source = "skillLevel", qualifiedByName = "fromSkillLevel")
    JobSkill toEntity(JobSkillDTO jobSkillDTO);

    @Named("toSkillLevel")
    default String toSkillLevel(SkillLevel level) {
        return level.getValue();
    }

    @Named("fromSkillLevel")
    default SkillLevel fromSkillLevel(String value) {
        if (value == null) return null;
        for (SkillLevel level : SkillLevel.values()) {
            if (level.getValue().equals(value)) {
                return level;
            }
        }
        return SkillLevel.BEGINNER;
    }
}
