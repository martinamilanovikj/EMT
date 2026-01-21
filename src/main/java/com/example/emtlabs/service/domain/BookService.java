package com.example.emtlabs.service.domain;

import com.example.emtlabs.model.domain.Book;
import com.example.emtlabs.model.enumerations.Category;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();


    Optional<Book> create(String name, Category category, Long authorId, LocalDate date,Integer availableCopies);
    Optional<Book> findById(Long id);

    List<Book> getLatestBook();
    Optional<Book> update(Long id,String name, Category category, Long authorId,LocalDate date, Integer availableCopies);
    void deleteById(Long id);
    Optional<Book> markBook(Long id);

}
