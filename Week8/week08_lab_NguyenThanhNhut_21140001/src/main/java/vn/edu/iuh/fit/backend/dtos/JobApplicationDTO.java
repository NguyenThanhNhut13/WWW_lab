/*
 * @ (#) JobApplicationDTO.java       1.0     17/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 17/11/2024
 * @version:    1.0
 */

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobApplicationDTO implements Serializable {
    private Long id;
    private JobDTO job;
    private CandidateDTO candidate;
    private LocalDate applyAt;
    private int status;
}
