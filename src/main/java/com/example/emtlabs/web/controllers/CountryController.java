package com.example.emtlabs.web.controllers;

import com.example.emtlabs.dto.CreateCountryDto;
import com.example.emtlabs.dto.DisplayCountryDto;
import com.example.emtlabs.dto.UpdateCountryDto;
import com.example.emtlabs.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/country")
@Tag(name = "Country API", description = "Endpoints for managing counties") // OpenAPI tag

public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController( CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;

    }


    @Operation(summary = "Get all counties", description = "Retrieves a list of all available counties.")
    @GetMapping
    public List<DisplayCountryDto> findAll() {
        return countryApplicationService.getAllCounties();
    }


    @Operation(summary = "Get country by ID", description = "Finds a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.getCountryId(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> create(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.createCountry(createCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryApplicationService.getCountryId(id).isPresent()) {
            countryApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Update an existing country", description = "Updates a county by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(
            @PathVariable Long id,
            @RequestBody UpdateCountryDto updateCountryDto
    ) {
        return countryApplicationService.updateCountry(id, updateCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}