/*
 * @ (#) CandidateMapper.java       1.0     07/11/2024
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
import org.mapstruct.factory.Mappers;
import vn.edu.iuh.fit.backend.dtos.CandidateDTO;
import vn.edu.iuh.fit.backend.models.Candidate;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    @Mapping(source = "user.id", target = "userId")
    CandidateDTO toDTO(Candidate candidate);

    @Mapping(source = "userId", target = "user.id")
    Candidate toEntity(CandidateDTO candidateDTO);

}
