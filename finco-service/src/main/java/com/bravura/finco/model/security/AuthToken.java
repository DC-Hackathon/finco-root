package com.bravura.finco.model.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthToken {
    @JsonProperty("access_token")
    private String access_token;
}
