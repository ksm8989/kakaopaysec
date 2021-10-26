package com.kakaopay.model.transaction;

public class Transaction {
    private String date;
    private String accountNumber;
    private Long amount;
    private String transactionId;
    private Long commission;
    private String isCancel;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public Transaction(String date, String accountNumber, String transactionId, Long amount, Long commission, String cancelYn) {
        this.date = date;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.transactionId = transactionId;
        this.commission = commission;
        this.isCancel = cancelYn;
    }
}
