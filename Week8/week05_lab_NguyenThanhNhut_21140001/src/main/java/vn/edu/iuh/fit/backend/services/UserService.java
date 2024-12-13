/*
 * @ (#) UserService.java       1.0     08/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 08/11/2024
 * @version:    1.0
 */

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.edu.iuh.fit.backend.dtos.UserDTO;
import vn.edu.iuh.fit.backend.models.User;

public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
    UserDTO getUserByUsername(String name);
}
