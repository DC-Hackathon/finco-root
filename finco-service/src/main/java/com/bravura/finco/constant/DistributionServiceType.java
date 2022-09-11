package com.bravura.finco.constant;

public enum DistributionServiceType {
    ASSET_DETAILS("asset details",1);
    private String code;
    private int value;

    DistributionServiceType(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setValue(int value) {
        this.value = value;
    }
}
