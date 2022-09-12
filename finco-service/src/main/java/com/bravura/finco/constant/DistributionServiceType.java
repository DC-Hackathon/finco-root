package com.bravura.finco.constant;

public enum DistributionServiceType {
    ASSET_DETAILS_WITH_ID("asset details",1),
    ALL_ASSET_DETAILS("all_asset_details",2),
    ALL_DISTRIBUTIONS_DATA("all_distributions_data",3),
    DISTRIBUTION_DATA_WITH_ID("distribution_data_with_id",4),
    NOMINEE_DETAILS("nominee_details",5),
    NOMINEE_DETAILS_WITH_ID("nominee_details_with_id",6),
    PRODUCT_DETAILS("product_details",7),
    PRODUCT_DETAILS_WITH_ID("product_details_with_id",8),
    INCOME_STREAM("income_stream",9);
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
