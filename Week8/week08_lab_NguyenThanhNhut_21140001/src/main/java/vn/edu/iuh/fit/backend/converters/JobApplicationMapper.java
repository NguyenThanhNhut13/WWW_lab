/*
 * @ (#) JobApplicationMapper.java       1.0     17/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.converters;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 17/11/2024
 * @version:    1.0
 */

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.iuh.fit.backend.dtos.JobApplicationDTO;
import vn.edu.iuh.fit.backend.models.JobApplication;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {
    JobApplicationMapper INSTANCE = Mappers.getMapper(JobApplicationMapper.class);

    JobApplicationDTO toDTO(JobApplication jobApplication);

    JobApplication toEntity(JobApplicationDTO jobApplicationDTO);
}
