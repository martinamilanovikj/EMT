package com.example.emt1.dto;

import com.example.emt1.model.domain.Country;

public record DisplayCountryDto(Long id, String name, String continent) {
    public static DisplayCountryDto from(Country country){
        return new DisplayCountryDto(country.getId() , country.getName(), country.getContinent());
    }
}