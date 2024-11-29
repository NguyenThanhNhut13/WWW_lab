/*
 * @ (#) SkillDTO.java       1.0     07/11/2024
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
import vn.edu.iuh.fit.backend.enums.SkillType;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkillDTO implements Serializable {
    private Long id;
    private String skillName;
    private SkillType type;
    private String description;
}
