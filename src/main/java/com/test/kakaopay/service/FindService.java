package com.test.kakaopay.service;

import com.test.kakaopay.model.account.Account;
import com.test.kakaopay.model.branch.Branch;
import com.test.kakaopay.model.transaction.Transaction;

import java.util.Map;

public interface FindService {
    public Map<String, Account> getAllAccounts();

    public Map<String, Branch> getAllBranches();

    public Map<String, Transaction> getAllTransactions();

    public Account getAccount(String acctNo);

    public Branch getBranch(String placeCd);

}
