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
import vn.edu.iuh.fit.demo.repositories.AccountRepository;
import vn.edu.iuh.fit.demo.repositories.impl.AccountRepositoryImpl;

public class AccountServices {
    private AccountRepository accountRepository;

    public AccountServices() {
        this.accountRepository = new AccountRepositoryImpl();
    }

    public boolean addAccount(Account account) {
        if (check(account)) {
            return false;
        } else {
            accountRepository.save(account);
            return true;
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
