package com.bravura.finco.constant;

public enum SonataServiceType {

    CLIENT("client", 1),
    ACCOUNT("account", 2);

    private String code;
    private int value;

    SonataServiceType(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
