package com.kakaopay.service;

import com.kakaopay.model.account.Account;
import com.kakaopay.model.branch.Branch;
import com.kakaopay.model.transaction.Transaction;

import java.util.Map;

public interface FindService {
    public Map<String, Account> getAllAccounts();

    public Map<String, Branch> getAllBranches();

    public Map<String, Transaction> getAllTransactions();

    public Account getAccount(String acctNo);

    public Branch getBranch(String placeCd);

}
