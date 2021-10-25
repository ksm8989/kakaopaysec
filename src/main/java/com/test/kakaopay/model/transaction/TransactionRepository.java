package com.test.kakaopay.model.transaction;

import java.util.Map;

public interface TransactionRepository {
    void save(Transaction transaction);

    Transaction findByDateAndTradeNo(String date, int tradeNo);

    Map<String, Transaction> findAllTransaction();
}
