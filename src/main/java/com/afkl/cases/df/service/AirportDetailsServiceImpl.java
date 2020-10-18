package com.afkl.cases.df.service;

import java.util.Locale;

import com.afkl.cases.df.common.RestTemplateTokenRequester;
import com.afkl.cases.df.common.TokenResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * This is an implemention class for Airport details.
 */
@Service
public class AirportDetailsServiceImpl implements AirportDetailsService {

    private RestTemplateTokenRequester restTemplateTokenRequester;
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(AirportDetailsServiceImpl.class);

    public AirportDetailsServiceImpl(RestTemplate restTemplate, RestTemplateTokenRequester restTemplateTokenRequester) {
        this.restTemplate = restTemplate;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
    }

/**
 * {@inheritDoc}
 * <p>
 * Gets the Airport codes with the locale and term as provided in the parameter.
 * Authorization required to access the fare API.
 * </p>
 * @param locale
 * @param term
 *            -code, name and description as term.
 */
    @Override
    public String findAirport(Locale locale, String term) {
        
        final String uri = "http://localhost:8080/airports/?locale=" + locale + "&&term=" + term;

        TokenResponse token = restTemplateTokenRequester.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        headers.set("Authorization", "Bearer " + token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new IllegalArgumentException("Error occurs during token generation");
        }
    }
}
