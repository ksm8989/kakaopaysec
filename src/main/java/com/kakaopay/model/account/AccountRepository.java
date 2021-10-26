package com.kakaopay.model.account;

import java.util.Map;

public interface AccountRepository {
    void save(Account account);

    Account findByAccountNumber(String accountNo);

    Map<String, Account> findAllAccount();
}
