package com.example.emt1.dto;

import com.example.emt1.model.domain.Author;
import com.example.emt1.model.domain.Book;
import com.example.emt1.model.enumerations.Category;

public record CreateBookDto(String name, Category category, Long authorId, Integer availableCopies) {
    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
