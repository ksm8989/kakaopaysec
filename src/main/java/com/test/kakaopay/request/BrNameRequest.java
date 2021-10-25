package com.test.kakaopay.request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrNameRequest {
    private String brName;

    public BrNameRequest() {
    }

    public String getBrName() {
        return brName;
    }

    public void setBrName(String brName) {
        this.brName = brName;
    }


}
