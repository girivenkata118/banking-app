package com.banking.app.service;

import com.banking.app.entity.Account;
import com.banking.app.exception.ResourceNotFoundException;
import com.banking.app.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));
    }

    public Account deposit(Long id, Double amount) {

        Account account = getAccountById(id);

        account.setBalance(account.getBalance() + amount);

        return accountRepository.save(account);
    }

    public Account withdraw(Long id, Double amount) {

        Account account = getAccountById(id);

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {

        Account account = getAccountById(id);

        accountRepository.delete(account);
    }
}