package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class GetClientResponse {

    @Schema(description = "")
    private ClientDetailType clientDetails = null;

    @Schema(description = "")
    private List<AddressDetailsType> addresses = null;

    @Schema(description = "")
    private List<BankAccountDetailType> bankAccounts = null;

    public ClientDetailType getClientDetails() {
        return clientDetails;
    }

    public List<AddressDetailsType> getAddresses() {
        return addresses;
    }

    public List<BankAccountDetailType> getBankAccounts() {
        return bankAccounts;
    }

}
