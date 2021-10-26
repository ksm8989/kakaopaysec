package com.kakaopay.service.account;

import com.kakaopay.model.account.Account;
import com.kakaopay.model.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountFinder {
    private AccountRepository accountRepository;

    @Autowired
    public AccountFinder(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Map<String, Account> findAll() {
        return accountRepository.findAllAccount();
    }

    public Account findBy(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}