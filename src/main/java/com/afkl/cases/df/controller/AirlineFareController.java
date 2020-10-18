package com.afkl.cases.df.controller;

import com.afkl.cases.df.service.Flight;
import com.afkl.cases.df.service.FlightFareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Flightfare Controller Class
 */
@RestController
public class AirlineFareController {

    @Autowired
    private FlightFareService flightFareService;

    /**
     * <p>
     * Gets the flight fare with the origin and destination as provided in the parameter.
     * Authorization required to access the fare API.
     * </p>
     *
     * @param origin      - The code of the origin.
     * @param destination - The code of the origin.
     * @return Flight
     */

    @RequestMapping(path = "/fares/{origin}/{destination}")
    public Flight getFlightFare(@PathVariable("origin") String origin, @PathVariable("destination") String destination,
                                @RequestParam(value = "currency", defaultValue = "EUR") String currency) {

        return flightFareService.findFlightsFare(origin, destination, currency);

    }
}

