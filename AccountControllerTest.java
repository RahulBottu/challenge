package com.dws.challenge;

import com.dws.challenge.controller.AccountController;
import com.dws.challenge.model.Account;
import com.dws.challenge.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account("123", "John Doe", 1000.00);
        doNothing().when(accountService).createAccount(account);

        accountController.createAccount(account);
        verify(accountService).createAccount(account);
    }

    @Test
    public void testTransferMoney() {
        String accountFrom = "123";
        String accountTo = "456";
        double amount = 100.00;

        doNothing().when(accountService).transferMoney(accountFrom, accountTo, amount);

        accountController.transferMoney(accountFrom, accountTo, amount);
        verify(accountService).transferMoney(accountFrom, accountTo, amount);
    }
}
