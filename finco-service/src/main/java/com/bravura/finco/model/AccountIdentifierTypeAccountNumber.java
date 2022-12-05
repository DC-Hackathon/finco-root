package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccountIdentifierTypeAccountNumber {

    @Schema(description = "account no")
    private String accountNo;
    @Schema(description = "product name")
    private String productName;
}
