package com.example.emtlabs.service.domain.Impl;


import com.example.emtlabs.model.domain.Book;
import com.example.emtlabs.model.enumerations.Category;
import com.example.emtlabs.model.exceptions.InvalidAuthorId;
import com.example.emtlabs.model.exceptions.InvalidBookIdException;
import com.example.emtlabs.repository.BookRepository;
import com.example.emtlabs.service.domain.AuthorService;
import com.example.emtlabs.service.domain.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();

    }

    @Override

    public Optional<Book> create(String name, Category category, Long authorId, LocalDate date ,Integer availableCopies) {
        return this.authorService.findById(authorId).map(author -> {
            Book book = new Book(name, category, author, date ,availableCopies);
            return Optional.of(this.bookRepository.save(book));
        }).orElse(Optional.empty());
    }


    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new));
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, LocalDate date,Integer availableCopies) {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(this.authorService.findById(authorId).orElseThrow(InvalidAuthorId::new));
        book.setAvailableCopies(availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }


    @Override
    public void deleteById(Long id) {

        bookRepository.deleteById(id);
    }


    public List<Book> getLatestBook(){
        return bookRepository.findTop10ByOrderByDateDesc();
    }
    @Override
    public Optional<Book> markBook(Long id) {
        return bookRepository.findById(id).map(book -> {
            book.markAsRented();
            return bookRepository.save(book);
        });
    }

}