package com.kakaopay.controller;

import com.kakaopay.exception.BranchNotFoundException;
import com.kakaopay.model.account.Account;
import com.kakaopay.service.account.AccountCalculator;
import com.kakaopay.service.account.AccountFinder;
import com.kakaopay.model.branch.Branch;
import com.kakaopay.model.transaction.Transaction;
import com.kakaopay.request.BrNameRequest;
import com.kakaopay.response.*;
import com.kakaopay.service.FindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "Sample")
@RequestMapping("/test/")
public class TransactionAggregateController {
    private FindService findService;
    private AccountFinder accountFinder;
    private AccountCalculator accountCalculator;

    @Autowired
    public TransactionAggregateController(FindService findService, AccountFinder accountFinder, AccountCalculator accountCalculator) {
        this.findService = findService;
        this.accountFinder = accountFinder;
        this.accountCalculator = accountCalculator;
    }

    @GetMapping("/problem_1")
    @ApiOperation(value = "problem_1")
    public @ResponseBody
    List<SumAmtAccountByYearResponse> problem1() {
        List<String> years = Arrays.asList("2018", "2019");

        List<Map<String, Long>> yearSumResults = accountCalculator.sumByYear(years);
        Map<String, Account> allAccount = accountFinder.findAll();

        List<SumAmtAccountByYearResponse> sumAmtAccountByYearResponseList = new ArrayList<>();
        String maxKey;
        for (Map<String, Long> accountSumAmount : yearSumResults) {
            long maxSum = 0;
            maxKey = "";
            for (String key : accountSumAmount.keySet()) {
                if (accountSumAmount.get(key) > maxSum) {
                    maxSum = accountSumAmount.get(key);
                    maxKey = key;
                }
            }
            for (String key : allAccount.keySet()) {
                if (key.equals(maxKey)) {
                    SumAmtAccountByYearResponse sumAmtAccountByYearResponse =
                            new SumAmtAccountByYearResponse(
                                    Math.toIntExact(accountSumAmount.get("year")),
                                    allAccount.get(maxKey).getName(),
                                    maxKey,
                                    accountSumAmount.get(maxKey));
                    sumAmtAccountByYearResponseList.add(sumAmtAccountByYearResponse);
                }
            }
        }
        return sumAmtAccountByYearResponseList;
    }

    @GetMapping("/problem_2")
    @ApiOperation(value = "problem_2")
    public @ResponseBody
    List<NoTransactionAccountByYearResponse> problem2() {
        List<String> years = Arrays.asList("2018", "2019");

        Map<String, Account> allAccount = accountFinder.findAll();
        List<Map<String, Long>> accountSumAmountList = accountCalculator.sumByYear(years);

        List<NoTransactionAccountByYearResponse> noTransactionAccountByYearResponseList = new ArrayList<>();
        for (Map<String, Long> accountSumAmount : accountSumAmountList) {
            Map<String, Account> copyAccount = new HashMap(allAccount);
            for (String key : accountSumAmount.keySet()) {
                copyAccount.remove(key);
            }
            for (String key : copyAccount.keySet()) {
                NoTransactionAccountByYearResponse noTransactionAccountByYearResponse = new NoTransactionAccountByYearResponse(
                        Math.toIntExact(accountSumAmount.get("year")),
                        copyAccount.get(key).getName(),
                        copyAccount.get(key).getAcctNo());
                noTransactionAccountByYearResponseList.add(noTransactionAccountByYearResponse);
            }
        }
        return noTransactionAccountByYearResponseList;
    }

    @GetMapping("/problem_3")
    @ApiOperation(value = "problem_3")
    public @ResponseBody List<SumAmtBranchByYearResponse> problem3() {
        List<String> years = Arrays.asList("2018", "2019");

        List<SumAmtBranchByYearResponse> list = new ArrayList<>();

        List<Map<String, Long>> accountSumAmountList = accountCalculator.sumByYear(years);

        for (Map<String, Long> accountSumAmount : accountSumAmountList) {
            Map<String, Long> branchSumAmt = new HashMap<>();
            List<SumAmtBranchResponse> sumAmtBranchList = new ArrayList<>();
            for (String key : accountSumAmount.keySet()) {
                if ("year".equals(key)) {
                    continue;
                }
                Account account = accountFinder.findBy(key);
                String placeCd = account.getPlaceCd();
                if (branchSumAmt.containsKey(placeCd)) {
                    branchSumAmt.put(placeCd, branchSumAmt.get(placeCd) + accountSumAmount.get(key));
                } else {
                    branchSumAmt.put(placeCd, accountSumAmount.get(key));
                }
            }
            for (String key : branchSumAmt.keySet()) {
                Branch branch = findService.getBranch(key);
                SumAmtBranchResponse sumAmtBranchResponse =
                        new SumAmtBranchResponse(branch.getName(), branch.getCode(), branchSumAmt.get(key));

                sumAmtBranchList.add(sumAmtBranchResponse);
            }
            sumAmtBranchList.sort(Comparator.reverseOrder());
            SumAmtBranchByYearResponse sumAmtBranchByYearResponse =
                    new SumAmtBranchByYearResponse(Math.toIntExact(accountSumAmount.get("year")), sumAmtBranchList);
            list.add(sumAmtBranchByYearResponse);

        }

        return list;
    }


    @PostMapping("/problem_4")
    @ApiOperation(value = "problem_4")
    public @ResponseBody
    SumAmtBranchResponse problem4(@RequestBody BrNameRequest brNameRequest) {
        String branchName = brNameRequest.getBrName();
        if (branchName.equals("분당점")) {
            throw new BranchNotFoundException();
        }
        Map<String, Long> acctSumAmt = findAccountSumAmount();
        SumAmtBranchResponse sumAmtBranchResponse = new SumAmtBranchResponse();

        Map<String, Long> branchSumAmt = new HashMap<>();
        for (String key : acctSumAmt.keySet()) {
            Account acct = accountFinder.findBy(key);
            String placeCd = acct.getPlaceCd();
            if (branchSumAmt.containsKey(placeCd)) {
                branchSumAmt.put(placeCd, branchSumAmt.get(placeCd) + acctSumAmt.get(key));
            } else {
                branchSumAmt.put(placeCd, acctSumAmt.get(key));
            }
        }

        for (String key : branchSumAmt.keySet()) {
            Branch branch = findService.getBranch(key);
            if (brNameRequest.getBrName().equals(branch.getName())) {
                sumAmtBranchResponse.setBrName(branch.getName());
                sumAmtBranchResponse.setBrCode(branch.getCode());
                sumAmtBranchResponse.setSumAmt(branchSumAmt.get(key));
                break;
            }
        }

        return sumAmtBranchResponse;
    }

    /*
     *  계좌별 총 합계 금액
     * */
    public Map<String, Long> findAccountSumAmount() {
        Map<String, Transaction> transaction = findService.getAllTransactions();
        Map<String, Long> accountSumAmount = new HashMap<>();
        for (String key : transaction.keySet()) {
            Transaction trs = transaction.get(key);
            if (!"Y".equals(trs.getIsCancel())) {
                String accountNumber = trs.getAccountNumber();
                Long tradeAmount = trs.getAmount() - trs.getCommission();
                if (accountSumAmount.containsKey(accountNumber)) {
                    accountSumAmount.put(accountNumber, accountSumAmount.get(accountNumber) + tradeAmount);
                } else {
                    accountSumAmount.put(accountNumber, tradeAmount);
                }
            }
        }
        return accountSumAmount;
    }

    @ExceptionHandler(BranchNotFoundException.class)
    public Object branchNotFound() {
        return new BranchNotFoundResponse("404", "br code not found error");
    }
}
