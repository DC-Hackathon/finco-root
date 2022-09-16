package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class BankAccountDetailType {
    @Schema(description = "")
    private Long id = null;

    @Schema(required = true, description = "")
    private CodeIdentifierType typeCode = null;

    @Schema(required = true, description = "")
    private String accountNumber = null;

    @Schema(required = true, description = "")
    private String accountName = null;


    public Long getId() {
        return id;
    }

    public CodeIdentifierType getTypeCode() {
        return typeCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }
}
