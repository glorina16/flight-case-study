package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private String origin;
    private String destination;
    private Currency currency;
    private double amount;

    @Override
    public String toString() {
        return "Flight [amount=" + amount + ", currency=" + currency + ", destination=" + destination + ", origin="
                + origin + "]";
    }


}
