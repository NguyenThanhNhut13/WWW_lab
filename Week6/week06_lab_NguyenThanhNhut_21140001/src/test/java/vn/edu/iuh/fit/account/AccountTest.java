/*
 * @ (#) AccountTest.java       1.0     19/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.account;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 19/10/2024
 * @version:    1.0
 */

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;

import java.util.Random;

@SpringBootTest
public class AccountTest {

    private AccountRepository accountRepository;

    @Autowired
    public AccountTest(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Test
    public void testInsert() {
        Random rd = new Random();
        for (long i = 0; i < 100; i++) {
            Account account = Account.builder()
                    .id(i)
                    .accountName("Account #" + i)
                    .accountNumber("ACC" + i)
                    .balance(rd.nextDouble() * 100000)
                    .build();
            accountRepository.save(account);
        }
    }


}
