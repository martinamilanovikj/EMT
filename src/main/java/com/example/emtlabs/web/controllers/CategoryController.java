package com.example.emtlabs.web.controllers;

import com.example.emtlabs.model.enumerations.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category API", description = "Endpoints for managing product categories")
public class CategoryController {

    @Operation(summary = "Get all categories", description = "Retrieves a list of all available categories from the enum.")
    @GetMapping
    public List<String> getAllCategories() {
        return Arrays.stream(Category.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}

