/*
 * @ (#) Candidate.java       1.0     15/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.entity;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 15/10/2024
 * @version:    1.0
 */
import lombok.*;
import vn.edu.iuh.fit.util.AppUtils;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Candidate {
    private Integer candidate_id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Date dob;
    private Set<CandidateSkill> candidateSkills = new HashSet<>();

    public String getFirstName() {
        return AppUtils.splitFullName(fullName)[0];
    }

    public String getMiddleName() {
        return AppUtils.splitFullName(fullName)[1];
    }

    public String getLastName() {
        return AppUtils.splitFullName(fullName)[2];
    }


}
