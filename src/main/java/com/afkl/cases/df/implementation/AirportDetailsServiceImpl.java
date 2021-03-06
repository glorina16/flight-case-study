package com.afkl.cases.df.implementation;

import java.util.Locale;

import com.afkl.cases.df.common.RestTemplateTokenRequester;
import com.afkl.cases.df.common.TokenResponse;

import com.afkl.cases.df.config.TravelApiConfig;
import com.afkl.cases.df.model.Airport;
import com.afkl.cases.df.service.AirportDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * This is an implementation class for Airport details.
 */
@Service
public class AirportDetailsServiceImpl implements AirportDetailsService {

    private final TravelApiConfig travelApiConfig;
    private final RestTemplateTokenRequester restTemplateTokenRequester;
    private final RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(AirportDetailsServiceImpl.class);

    public AirportDetailsServiceImpl(RestTemplate restTemplate, RestTemplateTokenRequester restTemplateTokenRequester,
                                     TravelApiConfig travelApiConfig) {
        this.restTemplate = restTemplate;
        this.restTemplateTokenRequester = restTemplateTokenRequester;
        this.travelApiConfig = travelApiConfig;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Gets the Airport codes with the locale and term as provided in the parameter.
     * Authorization required to access the fare API.
     * </p>
     *
     * @param locale
     * @param term   -code, name and description as term.
     * @param page   -number of pages
     * @return Airport object
     */

    @Override
    public Airport paginationOfAirport(Locale locale, String term, int page, int size) {

        final String uri = travelApiConfig.getAirportBaseUrl() + locale + "&&term=" + term + "&&page=" + page + "&&size=" + size;

        TokenResponse token = restTemplateTokenRequester.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        headers.set("Authorization", "Bearer " + token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Airport> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<Airport>() {
        });
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
