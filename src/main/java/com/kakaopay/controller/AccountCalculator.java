package com.kakaopay.controller;

import com.kakaopay.model.transaction.Transaction;
import com.kakaopay.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AccountCalculator {
    private FindService findService;

    @Autowired
    public AccountCalculator(FindService findService) {
        this.findService = findService;
    }

    public List<Map<String, Long>> sumByYear(List<String> years) {
        Map<String, Transaction> allTransactions = findService.getAllTransactions();

        return years.stream().map(year -> {
            Map<String, Long> accountSumAmount = new HashMap<>();
            for(String key : allTransactions.keySet()){
                Transaction transaction = allTransactions.get(key);
                if(transaction.matchYear(year) && !transaction.canceled()){
                    String accountNumber = transaction.getAccountNumber();
                    Long tradeAmount = transaction.getAmount() - transaction.getCommission();

                    if(accountSumAmount.containsKey(accountNumber)){
                        accountSumAmount.put(accountNumber, accountSumAmount.get(accountNumber) + tradeAmount);
                    }else{
                        accountSumAmount.put(accountNumber, tradeAmount);
                    }
                }
            }
            accountSumAmount.put("year", Long.parseLong(year));
            return accountSumAmount;
        }).collect(Collectors.toList());
    }
}
