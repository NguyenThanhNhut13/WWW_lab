/*
 * @ (#) AccountResource.java       1.0     19/10/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.recources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 19/10/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountResource {
    private AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        if (page != null) {
            return ResponseEntity.ok(accountService.getByPage(page, size));
        } else {
            return ResponseEntity.ok(accountService.getAll());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<Object> getByBalance(@RequestParam(value = "balance") Double balance, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        return ResponseEntity.ok(accountService.getByBalance(balance, page, size));
    }
}