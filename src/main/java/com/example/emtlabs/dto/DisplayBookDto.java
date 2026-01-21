package com.example.emtlabs.dto;

import com.example.emtlabs.model.domain.Book;
import com.example.emtlabs.model.enumerations.Category;

import java.time.LocalDate;

public record DisplayBookDto(Long id, String name, Category category, Long authorId, LocalDate date, Integer availableCopies) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getDate(),book.getAvailableCopies());
    }
}
