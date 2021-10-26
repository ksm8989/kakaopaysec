package com.kakaopay.response;

public class SumAmtBranchResponse implements Comparable<SumAmtBranchResponse> {
    private String brName;
    private String brCode;
    private Long sumAmt;

    public SumAmtBranchResponse() {
    }

    public SumAmtBranchResponse(String brName, String brCode, Long sumAmt) {
        this.brName = brName;
        this.brCode = brCode;
        this.sumAmt = sumAmt;
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName;
    }

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }

    public Long getSumAmt() {
        return sumAmt;
    }

    public void setSumAmt(Long sumAmt) {
        this.sumAmt = sumAmt;
    }


    @Override
    public int compareTo(SumAmtBranchResponse o) {
        long target = o.getSumAmt();
        if(sumAmt == target) return 0;
        else if(sumAmt > target) return 1;
        else return -1;
    }
}
