/*
 * @ (#) RoleServices.java       1.0     13/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.demo.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 13/09/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.demo.entities.GrantAccess;
import vn.edu.iuh.fit.demo.entities.GrantAccessId;
import vn.edu.iuh.fit.demo.entities.Role;
import vn.edu.iuh.fit.demo.repositories.AccountRepository;
import vn.edu.iuh.fit.demo.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.demo.repositories.RoleRepository;
import vn.edu.iuh.fit.demo.repositories.impl.AccountRepositoryImpl;
import vn.edu.iuh.fit.demo.repositories.impl.GrantAccessRepositoryImpl;
import vn.edu.iuh.fit.demo.repositories.impl.RoleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class RoleServices {
    private RoleRepository roleRepository;
    private GrantAccessRepository grantAccessRepository;
    private AccountRepository accountRepository;

    public RoleServices() {
        this.roleRepository = new RoleRepositoryImpl();
        this.grantAccessRepository = new GrantAccessRepositoryImpl();
        this.accountRepository = new AccountRepositoryImpl();
    }

    public boolean isAdministrator(String accountId) {
        return roleRepository.isAdministrator(accountId);
    }

    public List<String> findRoleByAccountId(String accountId) {
        List<GrantAccess> roles = grantAccessRepository.findRoleByAccountId(accountId);
        List<String> roleNames = new ArrayList<>();
        for (GrantAccess grantAccess : roles) {
            roleNames.add(grantAccess.getRole().getRoleName());
        }
        return roleNames;
    }

    public boolean grantRole(String accountId, String roleId) {
        if (grantAccessRepository.exists(accountId, roleId)) {
            return false;
        }

        Role role = roleRepository.findById(roleId);
        GrantAccess grantAccess = new GrantAccess();
        GrantAccessId grantAccessId = new GrantAccessId(role.getRoleId(), accountId);

        grantAccess.setId(grantAccessId);
        grantAccess.setRole(role);
        grantAccess.setAccount(accountRepository.findById(accountId));

        return grantAccessRepository.save(grantAccess);
    }

    public List<String> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<String> roleNames = new ArrayList<>();
        for (Role role : roles) {
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }


}
