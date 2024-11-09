/*
 * @ (#) UserService.java       1.0     10/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 10/11/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.backend.dtos.UserDTO;

public interface UserService {
    public UserDTO getUserById(Long id);
}
