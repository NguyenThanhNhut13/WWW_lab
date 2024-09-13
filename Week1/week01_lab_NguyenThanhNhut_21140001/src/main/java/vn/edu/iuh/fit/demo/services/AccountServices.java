/*
 * @ (#) AccountServices.java       1.0     13/09/2024
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

import vn.edu.iuh.fit.demo.entities.Account;
import vn.edu.iuh.fit.demo.entities.GrantAccess;
import vn.edu.iuh.fit.demo.entities.GrantAccessId;
import vn.edu.iuh.fit.demo.entities.Role;
import vn.edu.iuh.fit.demo.repositories.AccountRepository;
import vn.edu.iuh.fit.demo.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.demo.repositories.RoleRepository;
import vn.edu.iuh.fit.demo.repositories.impl.AccountRepositoryImpl;
import vn.edu.iuh.fit.demo.repositories.impl.GrantAccessRepositoryImpl;
import vn.edu.iuh.fit.demo.repositories.impl.RoleRepositoryImpl;

public class AccountServices {
    private AccountRepository accountRepository;
    private GrantAccessRepository grantAccessRepository;
    private RoleRepository roleRepository;

    public AccountServices() {
        this.accountRepository = new AccountRepositoryImpl();
        this.grantAccessRepository = new GrantAccessRepositoryImpl();
        this.roleRepository = new RoleRepositoryImpl();
    }

    public boolean addAccount(Account account) {
        if (check(account)) {
            return false;
        } else {
            accountRepository.save(account);

            Role userRole = roleRepository.findById("user");

            GrantAccess grantAccess = new GrantAccess();
            GrantAccessId grantAccessId = new GrantAccessId();
            grantAccessId.setAccountId(account.getAccountId());
            grantAccessId.setRoleId(userRole.getRoleId());

            grantAccess.setId(grantAccessId);
            grantAccess.setAccount(account);
            grantAccess.setRole(userRole);

            return grantAccessRepository.save(grantAccess);
        }
    }

    private boolean check(Account account) {
        AccountRepository accountRepository = new AccountRepositoryImpl();
        return accountRepository.exists(account.getAccountId());
    }

    public Account findAccountById(String id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(Account account) {
        return accountRepository.update(account);
    }

    public boolean deleteAccount(Account account) {
        return accountRepository.delete(account);
    }
}
