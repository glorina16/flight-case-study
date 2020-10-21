package com.afkl.cases.df.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class Page {
    private int number;

    private int size;

    private int totalPages;

    private int totalElements;

}
