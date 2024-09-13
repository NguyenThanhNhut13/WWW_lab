/*
 * @ (#) RoleRepository.java       1.0     13/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.demo.repositories;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 13/09/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.demo.entities.Role;

import java.util.List;

public interface RoleRepository {
    public List<Role> findAll();
    public Role findById(String id);
    public boolean exists(String id);
    public void save(Role role);
    public Role update(Role role);
    public boolean delete(Role role);
}
