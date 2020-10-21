package com.afkl.cases.df.controller;

import com.afkl.cases.df.common.RestTemplateTokenRequester;
import com.afkl.cases.df.common.TokenResponse;
import com.afkl.cases.df.config.TravelApiConfig;
import com.afkl.cases.df.implementation.FlightFareServiceImpl;
import com.afkl.cases.df.model.Currency;
import com.afkl.cases.df.model.Flight;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightFareServiceImplTest {

    private FlightFareServiceImpl flightFareServiceImpl;

    ResponseEntity responseEntity = mock(ResponseEntity.class);

    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_get_flight_fare() throws Exception {
        TravelApiConfig travelApiConfig = mock(TravelApiConfig.class);
        RestTemplate restTemplate = mock(RestTemplate.class);
        RestTemplateTokenRequester restTemplateTokenRequester = mock(RestTemplateTokenRequester.class);

        when(travelApiConfig.getFareBaseUrl()).thenReturn("http://localhost:8080/fares/");
        when(restTemplateTokenRequester.requestAccessToken()).thenReturn(new TokenResponse("123456", "bearer", "1234567",299, "read","mock"));

        flightFareServiceImpl = new FlightFareServiceImpl(restTemplate, restTemplateTokenRequester, travelApiConfig);

        Flight flight = new Flight();
        flight.setOrigin("BBI");
        flight.setDestination("DEL");
        flight.setCurrency(Currency.EUR);
        flight.setAmount(342.76);

        ResponseEntity<Flight> responseEntity = new ResponseEntity<>(new Flight("BBI", "DEL", Currency.EUR, 342.76), HttpStatus.OK);

        when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                ArgumentMatchers.<HttpEntity<String>> any(), ArgumentMatchers.<Class<Flight>> any())).thenReturn(responseEntity);
        Flight res = flightFareServiceImpl.findFlightsFare("BBI", "DEL","EUR");
        Assert.assertEquals(res, responseEntity.getBody());
    }

}