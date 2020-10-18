package com.afkl.cases.df.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Getter
    private String origin;
    @Getter
    private String destination;
    @Getter
    private Currency currency;
    @Getter
    private double amount;

    @Override
    public String toString() {
        return "Flight [amount=" + amount + ", currency=" + currency + ", destination=" + destination + ", origin="
                + origin + "]";
    }


}
