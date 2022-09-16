package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class CountryIdentifierType {

    @Schema(description = "")
    private Long id = null;

    @Schema(description = "")
    private String name = null;

    @Schema(description = "")
    private String code = null;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
