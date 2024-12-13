/*
 * @ (#) CompanyDTO.java       1.0     07/11/2024
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyDTO implements Serializable {
    private Long id;
    private Long userId;
    private String about;
    private String email;
    private String compName;
    private String phone;
    private String webUrl;
    private String logo;
    private AddressDTO address;

}
