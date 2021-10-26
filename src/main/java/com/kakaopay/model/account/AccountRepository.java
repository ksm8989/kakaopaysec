package com.kakaopay.model.account;

import java.util.Map;

public interface AccountRepository {
    void save(Account account);

    Account findByAcctNo(String acctNo);

    Map<String, Account> findAllAccount();
}
