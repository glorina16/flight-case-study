package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class Parent {
    private String code;

    private String name;

    private Coordinates coordinates;

    private String description;

}
