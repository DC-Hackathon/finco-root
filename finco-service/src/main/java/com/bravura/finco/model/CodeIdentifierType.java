package com.bravura.finco.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class CodeIdentifierType {

    @Schema(description = "")
    private Long id = null;

    @Schema(description = "")
    private String code = null;

    @Schema(description = "")
    private String codeType = null;

    @Schema(description = "")
    private String codeShortDescription = null;

    @Schema(description = "")
    private String codeDescription = null;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getCodeType() {
        return codeType;
    }

    public String getCodeShortDescription() {
        return codeShortDescription;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

}
