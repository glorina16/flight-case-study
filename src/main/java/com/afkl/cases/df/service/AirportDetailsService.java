package com.afkl.cases.df.service;

import com.afkl.cases.df.model.Airport;

import java.util.Locale;

/**
 * This is an interface for Airport details.
 */
public interface AirportDetailsService {

    /**
     * This method will be used to add two doubles.
     *
     * @param Locale
     * @param term   as code, name and description
     * @param page   -number of pages
     * @return Airport object
     */

    Airport paginationOfAirport(Locale local, String term, int page, int size );

}
