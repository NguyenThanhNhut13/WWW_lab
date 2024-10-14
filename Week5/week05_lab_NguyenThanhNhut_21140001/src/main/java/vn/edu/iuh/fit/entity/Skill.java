/*
 * @ (#) Skill.java       1.0     15/10/2024
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

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Skill {
    private Integer skill_id;
    private String skillName;
    private String description;
    private String field;
}
