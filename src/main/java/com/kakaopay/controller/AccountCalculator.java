package com.kakaopay.controller;

import com.kakaopay.model.transaction.Transaction;
import com.kakaopay.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountCalculator {
    private FindService findService;

    @Autowired
    public AccountCalculator(FindService findService) {
        this.findService = findService;
    }

    public List<Map<String, Long>> sumByYear(List<String> years) {
        Map<String, Transaction> allTransactions = findService.getAllTransactions();

        List<Map<String, Long>> returnList = new ArrayList<>();

        for(String year : years){
            Map<String, Long> accountSumAmt = new HashMap<>();
            for(String key : allTransactions.keySet()){
                Transaction trs = allTransactions.get(key);
                if(trs.getDate().substring(0, 4).equals(year) && !"Y".equals(trs.getIsCancel())){
                    String acctNo = trs.getAccountNumber();
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
}
