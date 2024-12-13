/*
 * @ (#) CandidateSkillDTO.java       1.0     07/11/2024
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

import lombok.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateSkillDTO implements Serializable {
    private CandidateSkillIdDTO id;
    private SkillDTO skill;
    private String moreInfos;
    private SkillLevel skillLevel;
    private LocalDate appliedDate;
}
