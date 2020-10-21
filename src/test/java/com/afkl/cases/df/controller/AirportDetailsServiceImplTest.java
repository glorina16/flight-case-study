package com.afkl.cases.df.controller;

import com.afkl.cases.df.common.RestTemplateTokenRequester;
import com.afkl.cases.df.common.TokenResponse;
import com.afkl.cases.df.config.TravelApiConfig;
import com.afkl.cases.df.implementation.AirportDetailsServiceImpl;
import com.afkl.cases.df.model.Airport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirportDetailsServiceImplTest {

    private AirportDetailsServiceImpl airportDetailsServiceImpl;

    ResponseEntity responseEntity = mock(ResponseEntity.class);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_get_airport_details() throws Exception {
        TravelApiConfig travelApiConfig = mock(TravelApiConfig.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        RestTemplateTokenRequester restTemplateTokenRequester = mock(RestTemplateTokenRequester.class);

        when(travelApiConfig.getFareBaseUrl()).thenReturn("http://localhost:8080/airports/?locale=");
        when(restTemplateTokenRequester.requestAccessToken()).thenReturn(new TokenResponse("123456", "bearer", "1234567",299, "read","mock"));

        airportDetailsServiceImpl = new AirportDetailsServiceImpl(restTemplate, restTemplateTokenRequester, travelApiConfig);
        File file = new File(getClass().getResource("/Airport.json").toURI());
        ObjectMapper objectMapper = new ObjectMapper();
        Airport airports = objectMapper.readValue(file, Airport.class);

        ResponseEntity<Airport> responseEntity = new ResponseEntity<>(airports, HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<String>> any(), any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
        Airport res = airportDetailsServiceImpl.paginationOfAirport(Locale.ENGLISH, "BBI",1, 2);
        Assert.assertEquals(res, responseEntity.getBody());
    }

    @Test
    public void should_handle_error() {
        TravelApiConfig travelApiConfig = mock(TravelApiConfig.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        RestTemplateTokenRequester restTemplateTokenRequester = mock(RestTemplateTokenRequester.class);

        when(travelApiConfig.getFareBaseUrl()).thenReturn("http://localhost:8080/airports/?locale=");
        when(restTemplateTokenRequester.requestAccessToken()).thenReturn(new TokenResponse("123456", "bearer", "1234567",299, "read","mock"));

        airportDetailsServiceImpl = new AirportDetailsServiceImpl(restTemplate, restTemplateTokenRequester, travelApiConfig);

        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<String>> any(), any(ParameterizedTypeReference.class))).thenThrow(new RuntimeException("Airport mock service down.."));

        assertThrows(RuntimeException.class, () -> airportDetailsServiceImpl.paginationOfAirport(Locale.ENGLISH, "BBI",1, 2), "Error thrown by Airport mock service");
    }
}
