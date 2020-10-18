package com.afkl.cases.df.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Airport {

    private String code, name, description;
    private Coordinates coordinates;
    private Airport parent;
    private Set<Airport> children;

}
