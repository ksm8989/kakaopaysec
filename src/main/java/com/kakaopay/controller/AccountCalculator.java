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
                Transaction transaction = allTransactions.get(key);
                if(transaction.matchYear(year) && !"Y".equals(transaction.getIsCancel())){
                    String accountNumber = transaction.getAccountNumber();
                    Long tradeAmt = transaction.getAmount() - transaction.getCommission();

                    if(accountSumAmt.containsKey(accountNumber)){
                        accountSumAmt.put(accountNumber, accountSumAmt.get(accountNumber) + tradeAmt);
                    }else{
                        accountSumAmt.put(accountNumber, tradeAmt);
                    }
                }
            }
            accountSumAmt.put("year", Long.parseLong(year));
            returnList.add(accountSumAmt);
        }

        return returnList;
    }
}
