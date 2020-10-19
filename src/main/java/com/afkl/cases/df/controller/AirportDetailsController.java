package com.afkl.cases.df.controller;

import java.util.Locale;

import com.afkl.cases.df.model.Airport;
import com.afkl.cases.df.service.AirportDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AirportDetails Controller Class
 */
@RestController
public class AirportDetailsController {

    @Autowired
    private AirportDetailsService airportDetailsService;

    /**
     * <p>
     * Gets the Airport codes with the locale and term as provided in the parameter.
     * Authorization required to access the fare API.
     * </p>
     *
     * @param term -code, name and description as term.
     * @param page   -number of pages
     *
     * @return Airport object
     */

    @RequestMapping(path = "/airports")
    public Airport getAirportsInPagination(@RequestParam(value = "lang", defaultValue = "en") String lang,
                                           @RequestParam("term") String term,
                                           @RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size) {

        Locale language = new Locale(lang);
        return airportDetailsService.paginationOfAirport(language, term, page, size);
    }
}

