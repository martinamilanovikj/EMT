package com.example.emt1.dto;

import com.example.emt1.model.domain.Author;
public record DisplayAuthorDto(Long id, String name, String surname, Long countryId) {

    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(author.getId(),author.getName(), author.getSurname(),author.getCountry().getId());
    }

}
