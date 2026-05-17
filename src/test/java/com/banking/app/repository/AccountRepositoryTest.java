package com.banking.app.repository;

import com.banking.app.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testSaveAccount() {
        Account account = new Account();
        account.setAccountHolderName("John Doe");
        account.setBalance(1000.0);

        Account saved = accountRepository.save(account);

        assertNotNull(saved.getId());
        assertEquals("John Doe", saved.getAccountHolderName());
    }
}