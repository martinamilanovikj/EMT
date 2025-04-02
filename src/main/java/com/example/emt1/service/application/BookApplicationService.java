package com.example.emt1.service.application;

import java.util.List;
import java.util.Optional;

import com.example.emt1.dto.CreateBookDto;
import com.example.emt1.dto.DisplayBookDto;
import com.example.emt1.dto.UpdateBookDto;


public interface BookApplicationService {
    List<DisplayBookDto> getAllBooks();
    Optional<DisplayBookDto> getBookId(Long id);
    Optional<DisplayBookDto>createBook(CreateBookDto createBookDto);
    Optional<DisplayBookDto>updateBook(Long id, UpdateBookDto updateBookDto);
    Optional <DisplayBookDto> markBook(Long id);
    void deleteById(Long id);
}
