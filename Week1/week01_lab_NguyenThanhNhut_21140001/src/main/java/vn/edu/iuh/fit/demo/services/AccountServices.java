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
import vn.edu.iuh.fit.demo.repositories.AccountDAO;
import vn.edu.iuh.fit.demo.repositories.impl.AccountDAOImpl;

public class AccountServices {
    private AccountDAO accountDAO;

    public AccountServices() {
        this.accountDAO = new AccountDAOImpl();
    }

    public boolean addAccount(Account account) {
        if (check(account)) {
            return false;
        } else {
            accountDAO.save(account);
            return true;
        }
    }

    private boolean check(Account account) {
        AccountDAO accountDAO = new AccountDAOImpl();
        return accountDAO.exists(account.getAccountId());
    }
}
