package com.afkl.cases.df.service;

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
 * This is an implemention class for Flight fares.
 */

@Service
public class FlightFareServiceImpl implements FlightFareService {

    private final RestTemplateTokenRequester restTemplateTokenRequester;
    private RestTemplate restTemplate = new RestTemplate();

    private static final Logger log = LoggerFactory.getLogger(FlightFareServiceImpl.class);

    public FlightFareServiceImpl(RestTemplate restTemplate, RestTemplateTokenRequester restTemplateTokenRequester) {
        this.restTemplate = restTemplate;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Gets the flight fare with the origin and destination as provided in the parameter.
     * Authorization required to access the fare API.
     * </p>
     *
     * @param origin      - The code of the origin.
     * @param destination - The code of the origin.
     * @return Flight
     */

    @Override
    public Flight findFlightsFare(String origin, String destination, String currency) {

        final String uri = "http://localhost:8080/fares/" + origin + "/" + destination + "?currency=" + currency;

        TokenResponse token = restTemplateTokenRequester.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        headers.set("Authorization", "Bearer " + token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Flight> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Flight.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new IllegalArgumentException("Error while call flight fare API");
        }
    }
}
