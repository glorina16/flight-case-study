package com.afkl.cases.df.service;

import java.util.Locale;

/**
 * This is an interface for Airport details.
 */
public interface AirportDetailsService {
    
    /**
   * This method will be used to add two doubles.
   * @param Locale 
   * @param term as code, name and description
   */
    String findAirport(Locale local, String term);
    
}
