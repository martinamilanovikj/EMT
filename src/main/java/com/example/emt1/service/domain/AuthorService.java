package com.example.emt1.service.domain;


import com.example.emt1.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(String name, String surname, Long countryId);
    Optional<Author> update(Long id,String name,String surname, Long countryId);

    void deleteById(Long id);

}
