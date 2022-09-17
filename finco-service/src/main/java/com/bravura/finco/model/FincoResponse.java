package com.bravura.finco.model;

import com.bravura.finco.model.NLPResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FincoResponse {
    @Schema(
            description = "NLP Response",
            example = "2.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("nlpResponse")
    private NLPResponse nlpResponse;

    @Schema(
            description = "Service Data",
            example = "2.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("data")
    private Object data;

    @Schema(
            description = "",
            example = "2.0",
            accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("queryResponse")
    private String queryResponse;
}
