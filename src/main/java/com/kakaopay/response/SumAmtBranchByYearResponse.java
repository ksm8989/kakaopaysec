package com.kakaopay.response;

import java.util.List;

public class SumAmtBranchByYearResponse {
    private int year;
    private List<SumAmtBranchResponse> dataList;

    public SumAmtBranchByYearResponse(int year, List<SumAmtBranchResponse> dataList) {
        this.year = year;
        this.dataList = dataList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<SumAmtBranchResponse> getDataList() {
        return dataList;
    }

    public void setDataList(List<SumAmtBranchResponse> dataList) {
        this.dataList = dataList;
    }
}
