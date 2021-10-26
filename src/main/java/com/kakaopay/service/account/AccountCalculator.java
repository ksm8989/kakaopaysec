package com.kakaopay.service.account;

import com.kakaopay.model.transaction.Transaction;
import com.kakaopay.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

            allTransactions
                    .values()
                    .stream()
                    .filter(transaction -> transaction.matchYear(year))
                    .filter(transaction -> !transaction.canceled())
                    .forEach(transaction -> {
                        sumAmount(accountSumAmount, transaction);
                    });

            accountSumAmount.put("year", Long.parseLong(year));
            return accountSumAmount;
        }).collect(Collectors.toList());
    }

    private void sumAmount(Map<String, Long> accountSumAmount, Transaction transaction) {
        String accountNumber = transaction.getAccountNumber();

        if(existAccountSum(accountSumAmount, accountNumber)){
            Long beforeSum = accountSumAmount.get(accountNumber);
            accountSumAmount.put(accountNumber, beforeSum + transaction.getTradeAmount());
        }else{
            accountSumAmount.put(accountNumber, transaction.getTradeAmount());
        }
    }

    private boolean existAccountSum(Map<String, Long> accountSumAmount, String accountNumber) {
        return accountSumAmount.containsKey(accountNumber);
    }
}
