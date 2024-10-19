/*
 * @ (#) AccountServiceImpl.java       1.0     19/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 19/10/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.services.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getByPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Account> pageAccount = accountRepository.findAll(pageRequest);
        return pageAccount.getContent();
    }

    @Override
    public List<Account> getByBalance(double amount, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size).withSort(Sort.by("balance").descending());
        Page<Account> pageAccount = accountRepository.findAccountByBalanceGreaterThan(amount, pageRequest);
        return pageAccount.getContent();
    }
}
