package com.dws.challenge.controller;

import com.dws.challenge.model.Account;
import com.dws.challenge.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public String createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        log.info("Account created successfully for ID: " + account.getId());
        return "Account created successfully!";
    }

    @PostMapping("/transfer")
    public String transferMoney(@RequestParam String accountFromId, @RequestParam String accountToId, @RequestParam double amount) {
        accountService.transferMoney(accountFromId, accountToId, amount);
        return "Transfer successful!";
    }
}
