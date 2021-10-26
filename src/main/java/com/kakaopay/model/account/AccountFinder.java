package com.kakaopay.model.account;

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
}
