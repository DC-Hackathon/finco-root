package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetClientResponse {

    @Schema(description = "")
    private Object clientDetails;

    @Schema(description = "")
    private Object addresses;

    @Schema(description = "")
    private Object bankAccounts;


}
