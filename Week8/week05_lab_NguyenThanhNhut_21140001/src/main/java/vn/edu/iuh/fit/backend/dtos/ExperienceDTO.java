/*
 * @ (#) ExperienceDTO.java       1.0     07/11/2024
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
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExperienceDTO implements Serializable {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    private String companyName;
    private String role;
    private String workDescription;
}
