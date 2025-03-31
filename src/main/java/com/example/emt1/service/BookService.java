package com.example.emt1.service;

import java.util.List;
import java.util.Optional;
import com.example.emt1.model.Book;
import com.example.emt1.model.Category;

public interface BookService {
    List<Book> findAll();
    Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> findById(Long id);

    Optional<Book> update(Long id,String name, Category category, Long authorId, Integer availableCopies);
    void deleteById(Long id);
    Optional<Book> markBook(Long id);
}
