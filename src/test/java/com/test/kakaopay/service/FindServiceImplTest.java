package com.test.kakaopay.service;

import com.test.kakaopay.AppConfig;
import com.test.kakaopay.model.account.Account;
import com.test.kakaopay.model.account.AccountRepository;
import com.test.kakaopay.model.branch.Branch;
import com.test.kakaopay.model.branch.BranchRepository;
import com.test.kakaopay.model.transaction.Transaction;
import com.test.kakaopay.model.transaction.TransactionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class FindServiceImplTest {
    private FindService findService;
    private AccountRepository accountRepository;
    private BranchRepository branchRepository;
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void init() {
        AppConfig appConfig = new AppConfig();
        accountRepository = appConfig.accountRepository();
        branchRepository = appConfig.branchRepository();
        transactionRepository = appConfig.transactionRepository();
        findService = new FindServiceImpl(accountRepository, branchRepository, transactionRepository);
    }

    @Test
    @DisplayName("getAccount O")
    void getAccount_o() {
        Account expected = new Account("11111117", "케빈", "C");
        accountRepository.save(expected);
        Account actual = findService.getAccount("11111117");
        Assertions.assertThat(expected).isEqualTo(actual);

    }

    @Test
    @DisplayName("getAccount X")
    void getAccount_x() {
        Account expected = new Account("11111117", "케빈", "C");
        Account actual = new Account("11111116", "린", "B");
        accountRepository.save(actual);

        Assertions.assertThat(expected).isEqualTo(findService.getAccount("11111117"));
    }

    @Test
    @DisplayName("getBranch O")
    void getBranch_o() {
        Branch expected = new Branch("A", "판교점");
        String name =  findService.getBranch("A").getName();
        Assertions.assertThat(expected.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("getBranch X")
    void getBranch_x() {
        Branch expected = new Branch("A", "판교점");
        Branch actual = new Branch("B", "분당점");
        branchRepository.save(expected);
        Assertions.assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("getAllAccounts")
    void getAllAccounts(){
        Map<String, Account> expected = accountRepository.findAllAccount();
        Assertions.assertThat(expected.containsKey("11111116"));
    }

    @Test
    @DisplayName("getAllBranches")
    void getAllBranches(){
        Map<String, Branch> expected = branchRepository.findAllBranch();
        Assertions.assertThat(expected.containsKey("A"));

    }

    @Test
    @DisplayName("getAllTransactions")
    void getAllTransactions(){
        Map<String, Transaction> expected = transactionRepository.findAllTransaction();
        Assertions.assertThat(expected.containsKey("201811111111"));
    }

}