package com.luxoft.springdb.lab1boot.dao;

import com.luxoft.springdb.lab1boot.model.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> getCountryList();
    List<Country> getCountryListStartWith(String name);
    void updateCountryName(String codeName, String newCountryName);
    Country getCountryByCodeName(String codeName);
    Country getCountryByName(String name);
}
