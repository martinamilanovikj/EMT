package com.example.emt1.web;

import com.example.emt1.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @GetMapping
    public List<Category> getAllCategories(){
       return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}

