package com.example.emt1.service.application.Impl;


import com.example.emt1.dto.CreateBookDto;
import com.example.emt1.dto.DisplayBookDto;
import com.example.emt1.dto.UpdateBookDto;
import com.example.emt1.model.domain.Author;
import com.example.emt1.service.application.BookApplicationService;
import com.example.emt1.service.domain.AuthorService;
import com.example.emt1.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
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


        return bookService.create(createBookDto.name(), createBookDto.category(), createBookDto.authorId(), createBookDto.availableCopies())
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
                updateBookDto.availableCopies()
        ).map(DisplayBookDto::from);
    }


    @Override
    public Optional<DisplayBookDto> markBook(Long id) {
        return bookService.markBook(id)
                .map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }
}
