package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SearchAccountResponse {

    @Schema(description = "client account")
    private List<SearchAccountResponseAccounts> accounts;
}
