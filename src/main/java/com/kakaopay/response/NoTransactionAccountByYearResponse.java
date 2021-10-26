package com.kakaopay.response;

public class NoTransactionAccountByYearResponse {
    private int year;
    private String name;
    private String acctNo;

    public NoTransactionAccountByYearResponse(int year, String name, String acctNo) {
        this.year = year;
        this.name = name;
        this.acctNo = acctNo;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getAcctNo() {
        return acctNo;
    }
}
