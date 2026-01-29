package com.example.emtlabs.dto;


import com.example.emtlabs.model.enumerations.Category;

import java.time.LocalDate;

public record UpdateBookDto(String name, Category category, Long authorId, LocalDate date, Integer availableCopies) {

}
