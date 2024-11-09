/*
 * @ (#) UserDTO.java       1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 10/11/2024
 * @version:    1.0
 */


import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private Instant registeredAt;
    private Instant lastLogin;
    private String intro;
    private String profile;
}
