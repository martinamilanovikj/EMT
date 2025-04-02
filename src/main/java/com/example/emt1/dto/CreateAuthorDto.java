package com.example.emt1.dto;

import com.example.emt1.model.domain.Author;
import com.example.emt1.model.domain.Country;

public record CreateAuthorDto(String name, String surname, Long countryId) {

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}