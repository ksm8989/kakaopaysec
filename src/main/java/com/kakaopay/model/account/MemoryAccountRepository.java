package com.kakaopay.model.account;

import java.util.HashMap;
import java.util.Map;

public class MemoryAccountRepository implements AccountRepository {
    private static Map<String, Account> store = new HashMap<>();

    @Override
    public void save(Account account) {
        store.put(account.getAcctNo(), account);
    }

    @Override
    public Account findByAccountNumber(String accountNo) {
        return store.get(accountNo);
    }

    @Override
    public Map<String, Account> findAllAccount() {
        return store;
    }
}
