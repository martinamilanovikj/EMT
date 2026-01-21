package com.example.emtlabs.dto;

import com.example.emtlabs.model.domain.Author;
import com.example.emtlabs.model.domain.Book;
import com.example.emtlabs.model.enumerations.Category;

import java.time.LocalDate;


public record CreateBookDto(String name, Category category, Long authorId, LocalDate date, Integer availableCopies) {
    public Book toBook(Author author) {
        return new Book(name, category, author, date, availableCopies);
    }
}
