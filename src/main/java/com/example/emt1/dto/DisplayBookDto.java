package com.example.emt1.dto;

import com.example.emt1.model.domain.Book;
import com.example.emt1.model.enumerations.Category;

public record DisplayBookDto(Long id, String name, Category category,Long authorId, Integer availableCopies) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies());
    }
}
