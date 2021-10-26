package com.kakaopay.model.account;

public class Account {
    private String acctNo;
    private String name;
    private String placeCd;
    public Account(String acctNo, String name, String placeCd) {
        this.acctNo = acctNo;
        this.name = name;
        this.placeCd = placeCd;
    }

    public void setId(String acctNo) {
        this.acctNo = acctNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace_cd(String placeCd) {
        this.placeCd = placeCd;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public String getName() {
        return name;
    }

    public String getPlaceCd() {
        return placeCd;
    }
}
