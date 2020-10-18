package com.afkl.cases.df.service;

/**
 * This is an interface for Flight fare.
 */
public interface FlightFareService {

    /**
     * This method will be used to add two doubles.
     *
     * @param origin      code to be provided
     * @param destination code to be provided
     * @param currency    as EUR or USD to be provided
     * @return Flight object
     */

    Flight findFlightsFare(String origin, String destination, String currency);

}
