package com.afkl.cases.df.common;

import java.util.Arrays;
import java.util.Objects;

import com.afkl.cases.df.config.TravelApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * This class generates token for accessing APIs.
 */

@Component
public class RestTemplateTokenRequester {

    private final TravelApiConfig travelApiConfig;
    private final RestTemplate restTemplate;

    public RestTemplateTokenRequester(TravelApiConfig travelApiConfig, RestTemplate restTemplate) {
        this.travelApiConfig = travelApiConfig;
        this.restTemplate = restTemplate;
    }

    public TokenResponse requestAccessToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(Objects.requireNonNull(travelApiConfig.getClientId()),
                Objects.requireNonNull(travelApiConfig.getSecretKey()));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("scope", travelApiConfig.getScope());
        map.add("grant_type", travelApiConfig.getGrandType());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<TokenResponse> response =
                restTemplate.exchange(travelApiConfig.getUrl(),
                        HttpMethod.POST,
                        entity,
                        TokenResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new IllegalArgumentException("Error occurs during token generation");
        }
    }

}