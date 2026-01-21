package com.example.emtlabs.service.application;

import com.example.emtlabs.dto.BooksPerAuthorView;
import com.example.emtlabs.dto.CreateBookDto;
import com.example.emtlabs.dto.DisplayBookDto;
import com.example.emtlabs.dto.UpdateBookDto;
import com.example.emtlabs.model.domain.Book;

import java.util.List;
import java.util.Optional;


public interface BookApplicationService {
    List<DisplayBookDto> getAllBooks();
    Optional<DisplayBookDto> getBookId(Long id);
    List<Book> getLatestBooks();
    Optional<DisplayBookDto>createBook(CreateBookDto createBookDto);
    Optional<DisplayBookDto>updateBook(Long id, UpdateBookDto updateBookDto);
    Optional <DisplayBookDto> markBook(Long id);
    void deleteById(Long id);
    List<BooksPerAuthorView> getBooksPerAuthor();
}
