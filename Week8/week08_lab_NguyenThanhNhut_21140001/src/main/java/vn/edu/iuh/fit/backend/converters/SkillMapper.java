/*
 * @ (#) SkillMapper.java       1.0     07/11/2024
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
import vn.edu.iuh.fit.backend.dtos.SkillDTO;
import vn.edu.iuh.fit.backend.enums.SkillType;
import vn.edu.iuh.fit.backend.models.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    @Mapping(target = "skillType", source = "type", qualifiedByName = "toSkillType")
    @Mapping(target = "description", source = "skillDescription")
    SkillDTO toDTO(Skill skill);

    @Mapping(target = "type", source = "skillType", qualifiedByName = "fromSkillType")
    @Mapping(target = "skillDescription", source = "description")
    Skill toEntity(SkillDTO skillDTO);

    @Named("toSkillType")
    default SkillType toSkillType(String value) {
        if (value == null) return null;
        for (SkillType type : SkillType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return SkillType.UNSPECIFIED;
    }

    @Named("fromSkillType")
    default String fromSkillType(SkillType skillType) {
        return skillType != null ? skillType.getValue() : null;
    }
}
