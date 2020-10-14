package com.afkl.cases.df;

import java.util.Arrays;

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
   
    private static final String TRAVEL_API_CLIENT = "travel-api-client";
    private static final String PSW = "psw";

    public TokenResponse requestAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
      
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); 
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(TRAVEL_API_CLIENT, PSW);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("scope","read");
        map.add("grant_type","client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<TokenResponse> response =
                restTemplate.exchange("http://localhost:8080/oauth/token",
                        HttpMethod.POST,
                        entity,
                        TokenResponse.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new IllegalArgumentException("Error occurs during token generation");
        }
    }

}