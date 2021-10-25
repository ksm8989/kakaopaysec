package com.test.kakaopay.service;

import com.test.kakaopay.CsvUtils;
import com.test.kakaopay.model.account.Account;
import com.test.kakaopay.model.account.AccountRepository;
import com.test.kakaopay.model.branch.Branch;
import com.test.kakaopay.model.branch.BranchRepository;
import com.test.kakaopay.model.transaction.Transaction;
import com.test.kakaopay.model.transaction.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindServiceImpl implements FindService{
    private final AccountRepository accountRepository;
    private final BranchRepository branchRepository;
    private final TransactionRepository transactionRepository;

    public FindServiceImpl(AccountRepository accountRepository, BranchRepository branchRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.branchRepository = branchRepository;
        this.transactionRepository = transactionRepository;


        List<List<String>> accountData = CsvUtils.readToList("account.csv");
        for(int i=1; i<accountData.size(); i++){
            Account account = new Account(accountData.get(i).get(0), accountData.get(i).get(1), accountData.get(i).get(2));
            accountRepository.save(account);
        }

        List<List<String>> branchData = CsvUtils.readToList("branch.csv");
        for(int i=1; i<branchData.size(); i++){
            Branch branch = new Branch(branchData.get(i).get(0), branchData.get(i).get(1));
            branchRepository.save(branch);
        }

        List<List<String>> transactionData = CsvUtils.readToList("transaction.csv");
        for(int i=1; i<transactionData.size(); i++){
            Transaction transaction = new Transaction(transactionData.get(i).get(0), transactionData.get(i).get(1), transactionData.get(i).get(2)
                                                        , Long.parseLong(transactionData.get(i).get(3)), Long.parseLong(transactionData.get(i).get(4)), transactionData.get(i).get(5));
            transactionRepository.save(transaction);
        }
    }

    @Override
    public Map<String, Account> getAllAccounts() {
        return accountRepository.findAllAccount();
    }

    @Override
    public Map<String, Branch> getAllBranches() {
        return branchRepository.findAllBranch();
    }

    @Override
    public Map<String, Transaction> getAllTransactions() {
        return transactionRepository.findAllTransaction();
    }

    @Override
    public Account getAccount(String acctNo) {
        return accountRepository.findByAcctNo(acctNo);
    }

    @Override
    public Branch getBranch(String placeCd) {
        return branchRepository.findByCode(placeCd);
    }
}
