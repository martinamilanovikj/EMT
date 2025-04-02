package com.example.emt1.dto;

import com.example.emt1.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}
