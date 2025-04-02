package com.example.emt1.web;

import com.example.emt1.dto.CreateAuthorDto;
import com.example.emt1.dto.DisplayAuthorDto;
import com.example.emt1.dto.UpdateAuthorDto;
import com.example.emt1.service.application.AuthorApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors") // OpenAPI tag
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
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
}
