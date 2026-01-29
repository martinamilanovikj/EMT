package com.example.emtlabs.service.application.Impl;


import com.example.emtlabs.dto.BooksPerAuthorView;
import com.example.emtlabs.dto.CreateBookDto;
import com.example.emtlabs.dto.DisplayBookDto;
import com.example.emtlabs.dto.UpdateBookDto;
import com.example.emtlabs.model.domain.Author;
import com.example.emtlabs.model.domain.Book;
import com.example.emtlabs.repository.BookRepository;
import com.example.emtlabs.service.application.BookApplicationService;
import com.example.emtlabs.service.domain.AuthorService;
import com.example.emtlabs.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final AuthorService authorService;


    public BookApplicationServiceImpl(BookRepository bookRepository, BookService bookService, AuthorService authorService) {
        this.bookRepository = bookRepository;

        this.bookService = bookService;
        this.authorService = authorService;
    }


    @Override

    public List<DisplayBookDto> getAllBooks() {
        return bookService.findAll().stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }


    @Override
    public Optional<DisplayBookDto> getBookId(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> createBook(CreateBookDto createBookDto) {

        Optional<Author> author = authorService.findById(createBookDto.authorId());
        if (author.isEmpty()) {
            return Optional.empty();
        }


        return bookService.create(createBookDto.name(), createBookDto.category(), createBookDto.authorId(), createBookDto.date(),createBookDto.availableCopies())
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> updateBook(Long id, UpdateBookDto updateBookDto) {
        Optional<Author> author = authorService.findById(updateBookDto.authorId());

        if (author.isEmpty()) {
            return Optional.empty();
        }

        return bookService.update(
                id,
                updateBookDto.name(),
                updateBookDto.category(),
                updateBookDto.authorId(),
                updateBookDto.date(),
                updateBookDto.availableCopies()
        ).map(DisplayBookDto::from);
    }


    @Override
    public Optional<DisplayBookDto> markBook(Long id) {
        return bookService.markBook(id)
                .map(DisplayBookDto::from);
    }

    public List<Book> getLatestBooks() {
    return bookService.getLatestBook();
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    public List<BooksPerAuthorView> getBooksPerAuthor() {
        return bookRepository.getBooksPerAuthor();
    }


}
