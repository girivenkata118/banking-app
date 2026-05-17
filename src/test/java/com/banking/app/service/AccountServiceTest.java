package com.banking.app.service;

import com.banking.app.entity.Account;
import com.banking.app.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccountById() {
        Account account = new Account();
        account.setId(1L);
        account.setAccountHolderName("John Doe");
        account.setBalance(1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account result = accountService.getAccountById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getAccountHolderName());
    }

    @Test
    void testDeposit() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(500.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account result = accountService.deposit(1L, 200.0);

        assertEquals(700.0, result.getBalance());
    }

    @Test
    void testWithdraw() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(500.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account result = accountService.withdraw(1L, 200.0);

        assertEquals(300.0, result.getBalance());
    }
}