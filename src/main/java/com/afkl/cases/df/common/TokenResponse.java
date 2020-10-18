package com.afkl.cases.df.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {
    @JsonProperty("access_token")
    @Getter private String accessToken;
    @JsonProperty("token_type")
    @Getter private String tokenType;
    @JsonProperty("refresh_token")
    @Getter private String refreshToken;
    @JsonProperty("expires_in")
    @Getter private Integer expiresIn;
    @JsonProperty("scope")
    @Getter private String scope;
    @JsonProperty("jti")
    @Getter private String jti;
}