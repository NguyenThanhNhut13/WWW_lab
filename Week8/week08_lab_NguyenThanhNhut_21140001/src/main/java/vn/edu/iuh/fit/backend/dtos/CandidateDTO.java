/*
 * @ (#) CandidateDTO.java       1.0     07/11/2024
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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateDTO implements Serializable {
    private Long id;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String phone;
    private AddressDTO address;
    private Set<CandidateSkillDTO> candidateSkills;
    private Set<ExperienceDTO> experiences;
}
