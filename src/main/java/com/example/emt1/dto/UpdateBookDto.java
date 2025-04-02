package com.example.emt1.dto;


import com.example.emt1.model.enumerations.Category;

public record UpdateBookDto(String name, Category category, Long authorId, Integer availableCopies) {

}
