package com.afkl.cases.df.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Locations {
    private Parent parent;

    private String code;

    private String name;

    private Coordinates coordinates;

    private String description;

}