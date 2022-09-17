package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GetAccountDetailsResponse {

    @Schema(description = "")
    private Object accountDetails;
}
