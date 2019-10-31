package com.luxoft.springdb.lab1boot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String name;
    private final String codeName;
}
