/*
 * @ (#) JobRecommendationDTO.java       1.0     07/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/12/2024
 * @version:    1.0
 */

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobRecommendationDTO {
    private Long jobId;
    private String jobName;
    private String jobDescription;
    private Double matchProbability;
}
