package com.kakaopay.service;

import com.kakaopay.model.account.Account;
import com.kakaopay.model.branch.Branch;
import com.kakaopay.model.transaction.Transaction;

import java.util.Map;

public interface FindService {
    Map<String, Transaction> getAllTransactions();

    Branch getBranch(String placeCd);

}
