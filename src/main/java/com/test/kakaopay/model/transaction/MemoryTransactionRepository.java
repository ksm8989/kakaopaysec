package com.test.kakaopay.model.transaction;

import java.util.HashMap;
import java.util.Map;

public class MemoryTransactionRepository implements TransactionRepository {

    private static Map<String, Transaction> store = new HashMap<>();

    @Override
    public void save(Transaction transaction) {
        String key = transaction.getDate() + transaction.getTradeNo();
        store.put(key, transaction);
    }

    @Override
    public Transaction findByDateAndTradeNo(String date, int tradeNo) {
        String key = date + tradeNo;
        return store.get(key);
    }

    @Override
    public Map<String, Transaction> findAllTransaction() {
        return store;
    }
}
