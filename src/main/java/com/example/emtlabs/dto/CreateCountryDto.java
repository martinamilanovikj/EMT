package com.example.emtlabs.dto;

import com.example.emtlabs.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry() {
        return new Country(name, continent);
    }
}
