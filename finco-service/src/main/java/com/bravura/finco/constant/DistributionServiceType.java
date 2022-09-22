package com.bravura.finco.constant;

public enum DistributionServiceType {
    ASSET_DETAILS_WITH_ID("asset",1),
    DISTRIBUTION_DATA_WITH_ID("voucher",2),
    NOMINEE_DETAILS_WITH_ID("nominee",3),
    PRODUCT_DETAILS_WITH_ID("product",4);
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
