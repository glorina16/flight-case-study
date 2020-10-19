package com.afkl.cases.df.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private int number;

    private int size;

    private int totalPages;

    private int totalElements;

}
