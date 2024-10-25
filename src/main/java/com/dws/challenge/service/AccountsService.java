package com.dws.challenge.service;

import com.dws.challenge.model.Account;
import com.dws.challenge.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    private final Lock lock = new ReentrantLock();

    public void transferMoney(String accountFromId, String accountToId, double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        lock.lock();
        try {
            Account accountFrom = accountRepository.getAccount(accountFromId);
            Account accountTo = accountRepository.getAccount(accountToId);

            if (accountFrom == null || accountTo == null) {
                throw new IllegalArgumentException("Invalid account details provided");
            }

            if (accountFrom.getBalance() < amount) {
                throw new IllegalArgumentException("Insufficient funds in account");
            }

            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);

            accountRepository.updateAccount(accountFrom);
            accountRepository.updateAccount(accountTo);

            log.info("Transfer completed successfully");
        } finally {
            lock.unlock();
        }
    }

    public void createAccount(Account account) {
        accountRepository.createAccount(account);
    }
}