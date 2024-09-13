/*
 * @ (#) GrantAccessRepository.java       1.0     13/09/2024
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

import vn.edu.iuh.fit.demo.entities.GrantAccess;

import java.util.List;

public interface GrantAccessRepository {
    public List<GrantAccess> findAccountByRoleId(String roleId);
    public List<GrantAccess> findRoleByAccountId(String accountId);
    public boolean save(GrantAccess grantAccess);
    public boolean delete(GrantAccess grantAccess);
    public boolean exists(String accountId, String roleId);

}
