package com.kakaopay.response;

public class SumAmtAccountByYearResponse {
    private int year;
    private String name;
    private String acctNo;
    private Long sumAmt;

    public SumAmtAccountByYearResponse() {
    }

    public SumAmtAccountByYearResponse(int year, String name, String acctNo, Long sumAmt) {
        this.year = year;
        this.name = name;
        this.acctNo = acctNo;
        this.sumAmt = sumAmt;
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

    public Long getSumAmt() {
        return sumAmt;
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

    public void setSumAmt(Long sumAmt) {
        this.sumAmt = sumAmt;
    }
}
