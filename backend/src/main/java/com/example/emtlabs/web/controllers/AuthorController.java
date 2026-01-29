package com.example.emtlabs.web.controllers;

import com.example.emtlabs.dto.CreateAuthorDto;
import com.example.emtlabs.dto.DisplayAuthorDto;
import com.example.emtlabs.dto.UpdateAuthorDto;
import com.example.emtlabs.model.projections.AuthorNameProjection;
import com.example.emtlabs.model.views.AuthorsPerCountryView;
import com.example.emtlabs.repository.AuthorsPerCountryViewRepository;
import com.example.emtlabs.service.application.AuthorApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/author")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public AuthorController(AuthorApplicationService authorApplicationService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.authorApplicationService = authorApplicationService;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }


    @Operation(summary = "Get all authors", description = "Retrieves a list of all available authors.")
    @GetMapping
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.getAllAuthors();
    }

    @Operation(summary = "Get author by ID", description = "Finds an author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.getAuthorById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new author", description = "Creates a new author.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> create(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.createAuthor(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing author", description = "Updates an author by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> update(
            @PathVariable Long id,
            @RequestBody UpdateAuthorDto updateAuthorDto
    ) {
        return authorApplicationService.updateAuthor(id, updateAuthorDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (authorApplicationService.getAuthorById(id).isPresent()) {
            authorApplicationService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get all author names", description = "Returns only the names and surnames of all authors.")
    @GetMapping("/names")
    public List<AuthorNameProjection> findAllAuthorNames() {
        return authorApplicationService.getAllAuthorNames();
    }

    @GetMapping("/per-country")
    public List<AuthorsPerCountryView> getAuthorsByCountry() {
        return authorsPerCountryViewRepository.findAll();
    }


}

