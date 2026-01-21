package com.example.emtlabs.web.controllers;

import com.example.emtlabs.dto.BooksPerAuthorView;
import com.example.emtlabs.dto.CreateBookDto;
import com.example.emtlabs.dto.DisplayBookDto;
import com.example.emtlabs.dto.UpdateBookDto;
import com.example.emtlabs.model.domain.Book;
import com.example.emtlabs.service.application.BookApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book API", description = "Endpoints for managing books") // OpenAPI tag
public class BookController {

    private final BookApplicationService bookApplicationService;

    public BookController( BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all available books.")
    @GetMapping
    public List<DisplayBookDto> findAll() {
        return bookApplicationService.getAllBooks();
    }



    @Operation(summary = "Get book by ID", description = "Finds a book by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.getBookId(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a new book", description = "Creates a new book.")
    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> create (@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.createBook(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing book", description = "Updates a book by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayBookDto> update(
            @PathVariable Long id,
            @RequestBody UpdateBookDto updateBookDto
    ) {
        return bookApplicationService.updateBook(id, updateBookDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a book", description = "Deletes a book by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (bookApplicationService.getBookId(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Mark book as rented", description = "Marks a book as rented by its ID.")
    @PutMapping("/{id}/mark-rented")
    public ResponseEntity<DisplayBookDto> markBookAsRented(@PathVariable Long id) {
        Optional<DisplayBookDto> updatedBook = bookApplicationService.markBook(id);

        return updatedBook
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/latest")
    public List<Book> getLatestBooks(){
        return bookApplicationService.getLatestBooks();
    }

    @GetMapping("/per-author")
    @Operation(summary = "Get number of books per author", description = "Retrieves the number of books per author from the materialized view.")
    public List<BooksPerAuthorView> getBooksPerAuthor() {
        return bookApplicationService.getBooksPerAuthor();
    }

}