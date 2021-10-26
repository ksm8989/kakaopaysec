package com.kakaopay.service;

import com.kakaopay.model.account.Account;
import com.kakaopay.model.branch.Branch;
import com.kakaopay.model.transaction.Transaction;

import java.util.Map;

public interface FindService {
    Map<String, Account> getAllAccounts();

    Map<String, Transaction> getAllTransactions();

    Account getAccount(String acctNo);

    Branch getBranch(String placeCd);

}
