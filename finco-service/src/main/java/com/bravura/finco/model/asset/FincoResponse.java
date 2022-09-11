package com.bravura.finco.model.asset;

import com.bravura.finco.model.NlpResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public class FincoResponse<T> {
    @Schema(
            description = "NLP Response",
            example = "2.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("nlpResponse")
    private NlpResponse nlpResponse;

    @Schema(
            description = "Service Data",
            example = "2.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("data")
    private T data;
}
