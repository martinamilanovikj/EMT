package com.example.emtlabs.dto;

import com.example.emtlabs.model.domain.Author;
import com.example.emtlabs.model.domain.Country;

public record CreateAuthorDto(String name, String surname, Long countryId) {

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}