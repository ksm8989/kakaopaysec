package com.kakaopay.controller;

import com.kakaopay.exception.BrCodeNotFoundException;
import com.kakaopay.model.account.Account;
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
    @Autowired
    private FindService findService;

    @GetMapping("/problem_1")
    @ApiOperation(value = "problem_1")
    public @ResponseBody List<SumAmtAccountByYearResponse> problem1(){
        String maxKey = "";
        List<SumAmtAccountByYearResponse> sumAmtAccountByYearResponseList = new ArrayList<>();

        // 2018, 2019
        List<String> years = this.getYears();

        // 연도별 계좌별 금액의 합
        List<Map<String, Long>> acctSumAmtList = findAccountSumAmtByYear(years);

        // 모든 계좌정보 조회
        Map<String, Account> allAccount = findService.getAllAccounts();


        for(Map<String, Long> acctSumAmt : acctSumAmtList){
            long maxSum = 0;
            maxKey = "";
            for(String key : acctSumAmt.keySet()){
                if(acctSumAmt.get(key) > maxSum){
                    maxSum = acctSumAmt.get(key);
                    maxKey = key;
                }
            }
            for(String key : allAccount.keySet()){
                if(key.equals(maxKey)){
                    SumAmtAccountByYearResponse sumAmtAccountByYearResponse =
                            new SumAmtAccountByYearResponse( Math.toIntExact(acctSumAmt.get("year")), allAccount.get(maxKey).getName(), maxKey, acctSumAmt.get(maxKey));
                    sumAmtAccountByYearResponseList.add(sumAmtAccountByYearResponse);
                }
            }
        }
        return sumAmtAccountByYearResponseList;
    }

    @GetMapping("/problem_2")
    @ApiOperation(value = "problem_2")
    public @ResponseBody List<NoTransactionAccountByYearResponse> problem2(){

        List<NoTransactionAccountByYearResponse> noTransactionAccountByYearResponseList = new ArrayList<>();
        // 모든 계좌정보 조회
        Map<String, Account> allAccount = findService.getAllAccounts();
        // 2018, 2019
        List<String> years = this.getYears();
        // 연도별 계좌별 금액의 합
        List<Map<String, Long>> acctSumAmtList = findAccountSumAmtByYear(years);


        for(Map<String, Long> acctSumAmt : acctSumAmtList){
            Map<String, Account> copyAccount = new HashMap<String, Account>(allAccount);
            for(String key : acctSumAmt.keySet()){
                copyAccount.remove(key);
            }
            for(String key : copyAccount.keySet()){
                NoTransactionAccountByYearResponse noTransactionAccountByYearResponse = new NoTransactionAccountByYearResponse(Math.toIntExact(acctSumAmt.get("year")), copyAccount.get(key).getName(), copyAccount.get(key).getAcctNo());
                noTransactionAccountByYearResponseList.add(noTransactionAccountByYearResponse);
            }
        }
        return noTransactionAccountByYearResponseList;
    }

    @GetMapping("/problem_3")
    @ApiOperation(value = "problem_3")
    public @ResponseBody List<SumAmtBranchByYearResponse> problem3(){
        List<SumAmtBranchByYearResponse> list = new ArrayList<>();
        List<String> years = this.getYears();

        //계좌별 연도별 총 금액
        List<Map<String, Long>> acctSumAmtList = findAccountSumAmtByYear(years);

        for(Map<String, Long> acctSumAmt : acctSumAmtList){
            Map<String, Long> branchSumAmt = new HashMap<>();
            List<SumAmtBranchResponse> sumAmtBranchList = new ArrayList<>();
            for(String key : acctSumAmt.keySet()){
                if("year".equals(key)){
                    continue;
                }
                Account acct = findService.getAccount(key);
                String placeCd = acct.getPlaceCd();
                if(branchSumAmt.containsKey(placeCd)){
                    branchSumAmt.put(placeCd, branchSumAmt.get(placeCd) + acctSumAmt.get(key));
                }else{
                    branchSumAmt.put(placeCd, acctSumAmt.get(key));
                }
            }
            for(String key : branchSumAmt.keySet()){
                Branch branch = findService.getBranch(key);
                SumAmtBranchResponse sumAmtBranchResponse =
                        new SumAmtBranchResponse(branch.getName(), branch.getCode(), branchSumAmt.get(key));

                sumAmtBranchList.add(sumAmtBranchResponse);
            }
            sumAmtBranchList.sort(Comparator.reverseOrder());
            SumAmtBranchByYearResponse sumAmtBranchByYearResponse =
                    new SumAmtBranchByYearResponse(Math.toIntExact(acctSumAmt.get("year")), sumAmtBranchList);
            list.add(sumAmtBranchByYearResponse);

        }

        return list;
    }


    @PostMapping("/problem_4")
    @ApiOperation(value = "problem_4")
    public @ResponseBody SumAmtBranchResponse problem4(@RequestBody BrNameRequest brNameRequest){
        //BrNameRequest brNameRequest = new BrNameRequest("분당점");
        String branchName = brNameRequest.getBrName();
        if(branchName.equals("분당점")){
            throw new BrCodeNotFoundException();
        }
        Map<String, Long> acctSumAmt = findAccountSumAmt();
        SumAmtBranchResponse sumAmtBranchResponse = new SumAmtBranchResponse();

        Map<String, Long> branchSumAmt = new HashMap<>();
        for(String key : acctSumAmt.keySet()){
            Account acct = findService.getAccount(key);
            String placeCd = acct.getPlaceCd();
            if(branchSumAmt.containsKey(placeCd)){
                branchSumAmt.put(placeCd, branchSumAmt.get(placeCd) + acctSumAmt.get(key));
            }else{
                branchSumAmt.put(placeCd, acctSumAmt.get(key));
            }
        }

        for(String key : branchSumAmt.keySet()){
            Branch branch = findService.getBranch(key);
            if(brNameRequest.getBrName().equals(branch.getName())){
                sumAmtBranchResponse.setBrName(branch.getName());
                sumAmtBranchResponse.setBrCode(branch.getCode());
                sumAmtBranchResponse.setSumAmt(branchSumAmt.get(key));
                break;
            }
        }

        return sumAmtBranchResponse;
    }

    public List<String> getYears() {
        return Arrays.asList("2018", "2019");
    }

    /*
     *  계좌별 연도별 합계 금액
     * */
    public List<Map<String, Long>> findAccountSumAmtByYear(List<String> years) {
        Map<String, Transaction> transaction = findService.getAllTransactions();
        List<Map<String, Long>> returnList = new ArrayList<>();

        for(String year : years){
            Map<String, Long> accountSumAmt = new HashMap<>();
            for(String key : transaction.keySet()){
                Transaction trs = transaction.get(key);
                if(trs.getDate().substring(0, 4).equals(year) && !"Y".equals(trs.getCancelYn())){
                    String acctNo = trs.getAcctNo();
                    Long tradeAmt = trs.getAmount() - trs.getCommission();

                    if(accountSumAmt.containsKey(acctNo)){
                        accountSumAmt.put(acctNo, accountSumAmt.get(acctNo) + tradeAmt);
                    }else{
                        accountSumAmt.put(acctNo, tradeAmt);
                    }
                }
            }
            accountSumAmt.put("year", Long.parseLong(year));
            returnList.add(accountSumAmt);
        }

        return returnList;
    }


    /*
     *  계좌별 총 합계 금액
     * */
    public Map<String, Long> findAccountSumAmt(){
        Map<String, Transaction> transaction = findService.getAllTransactions();
        List<Map<String, Long>> returnList = new ArrayList<>();
        Map<String, Long> accountSumAmt = new HashMap<>();
        for(String key : transaction.keySet()){
            Transaction trs = transaction.get(key);
            if(!"Y".equals(trs.getCancelYn())){
                String acctNo = trs.getAcctNo();
                Long tradeAmt = trs.getAmount() - trs.getCommission();
                if(accountSumAmt.containsKey(acctNo)){
                    accountSumAmt.put(acctNo, accountSumAmt.get(acctNo) + tradeAmt);
                }else{
                    accountSumAmt.put(acctNo, tradeAmt);
                }
            }
        }
        return accountSumAmt;
    }

    @ExceptionHandler(BrCodeNotFoundException.class)
    public Object brCodeNotFound(){
        BrCodeNotFoundResponse brCodeNotFoundResponse = new BrCodeNotFoundResponse("404", "br code not found error");
        return brCodeNotFoundResponse;
    }
}
