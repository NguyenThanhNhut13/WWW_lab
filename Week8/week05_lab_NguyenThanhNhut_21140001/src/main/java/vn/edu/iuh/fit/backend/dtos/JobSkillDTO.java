/*
 * @ (#) JobSkillDTO.java       1.0     07/11/2024
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

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import vn.edu.iuh.fit.backend.enums.SkillLevel;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobSkillDTO implements Serializable {
    private JobSkillIdDTO id;
    private SkillDTO skill;
    private SkillLevel skillLevel;
    private String moreInfos;

}
