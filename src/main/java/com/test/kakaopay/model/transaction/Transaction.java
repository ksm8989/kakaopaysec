package com.test.kakaopay.model.transaction;

public class Transaction {
    private String date;
    private String acctNo;
    private Long amount;
    private String tradeNo;
    private Long commission;
    private String cancelYn;

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

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public String getCancelYn() {
        return cancelYn;
    }

    public void setCancelYn(String cancelYn) {
        this.cancelYn = cancelYn;
    }

    public Transaction(String date, String acctNo, String tradeNo, Long amount,  Long commission, String cancelYn) {
        this.date = date;
        this.amount = amount;
        this.acctNo = acctNo;
        this.tradeNo = tradeNo;
        this.commission = commission;
        this.cancelYn = cancelYn;
    }
}
