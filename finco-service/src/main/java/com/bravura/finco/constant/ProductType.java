package com.bravura.finco.constant;

public enum ProductType  {
    SONATA("sonata",1),
    DISTRIBUTIONS("distributions",2);

    private String code;
    private int value;

    ProductType(String code, int value) {
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
