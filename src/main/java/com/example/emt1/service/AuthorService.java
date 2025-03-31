package com.example.emt1.service;

import com.example.emt1.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> create(String name, String surname, Long countryId);
    Optional<Author> findById(Long id);


    Optional<Author> update(Long id,String name, String surname, Long countryId);

    void deleteById(Long id);

}
