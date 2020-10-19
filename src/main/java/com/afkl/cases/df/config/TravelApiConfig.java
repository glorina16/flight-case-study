package com.afkl.cases.df.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TravelApiConfig {
    @Value("${clientId}")
    @Getter
    private String clientId;

    @Value("${secretKey}")
    @Getter
    private String secretKey;

    @Value("${scope}")
    @Getter
    private String scope;

    @Value("${grandType}")
    @Getter
    private String grandType;

    @Value("${url}")
    @Getter
    private String url;

    @Value("${airportBaseUrl}")
    @Getter
    private String airportBaseUrl;

    @Value("${fareBaseUrl}")
    @Getter
    private String fareBaseUrl;
}
