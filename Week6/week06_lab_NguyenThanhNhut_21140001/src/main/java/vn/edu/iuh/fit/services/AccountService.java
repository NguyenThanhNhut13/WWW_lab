/*
 * @ (#) AccountService.java       1.0     19/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 19/10/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.models.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAll();
    public List<Account> getByPage(int page, int size);
    public List<Account> getByBalance(double amount, int page, int size);
}
