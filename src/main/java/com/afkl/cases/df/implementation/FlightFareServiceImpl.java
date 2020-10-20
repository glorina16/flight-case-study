package com.afkl.cases.df.implementation;

import com.afkl.cases.df.common.RestTemplateTokenRequester;
import com.afkl.cases.df.common.TokenResponse;

import com.afkl.cases.df.config.TravelApiConfig;
import com.afkl.cases.df.model.Flight;
import com.afkl.cases.df.service.FlightFareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * This is an implementation class for Flight fares.
 */

@Service
public class FlightFareServiceImpl implements FlightFareService {

    private final TravelApiConfig travelApiConfig;
    private final RestTemplateTokenRequester restTemplateTokenRequester;
    private final RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(FlightFareServiceImpl.class);

    public FlightFareServiceImpl(RestTemplate restTemplate, RestTemplateTokenRequester restTemplateTokenRequester,
                                 TravelApiConfig travelApiConfig) {
        this.restTemplate = restTemplate;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
        this.travelApiConfig = travelApiConfig;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Gets the flight fare with the origin and destination as provided in the parameter.
     * Authorization required to access the fare API.
     * </p>
     *
     * @param origin      - The code of the origin.
     * @param destination - The code of the destination.
     * @return Flight
     */

    @Override
    public Flight findFlightsFare(String origin, String destination, String currency) {

        final String uri = travelApiConfig.getFareBaseUrl() + origin + "/" + destination + "?currency=" + currency;

        TokenResponse token = restTemplateTokenRequester.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        headers.set("Authorization", "Bearer " + token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Flight> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Flight.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        if (response.getStatusCode().is4xxClientError()) {
            log.error("Rest service [{}] responded with bad request.", uri);
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        if (response.getStatusCode().is5xxServerError()) {
            log.error("Rest service [{}] returned server error.", uri);
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            throw new IllegalArgumentException("Error occurs during token generation");
        }
    }
}
