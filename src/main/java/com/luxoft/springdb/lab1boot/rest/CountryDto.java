package com.luxoft.springdb.lab1boot.rest;

import com.luxoft.springdb.lab1boot.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class CountryDto {
    private int id = -1;
    private String name;
    private String codeName;


    static Country toDomainObject(CountryDto dto) {
        return new Country(dto.getId(), dto.getName(), dto.getCodeName());
    }

    static CountryDto toDto(Country country) {
        return new CountryDto(country.getId(), country.getName(), country.getCodeName());
    }
}
