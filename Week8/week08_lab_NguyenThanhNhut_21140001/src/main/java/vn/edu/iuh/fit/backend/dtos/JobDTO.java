/*
 * @ (#) JobDTO.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobDTO implements Serializable {
    private Long id;
    private String jobDesc;
    private String jobName;
    private CompanyDTO company;
    private String salary;
    private int status;
    private double matchPercentage;
    private List<JobSkillDTO> jobSkills;
}
