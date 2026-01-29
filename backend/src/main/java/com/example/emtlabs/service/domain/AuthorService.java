package com.example.emtlabs.service.domain;


import com.example.emtlabs.model.domain.Author;
import com.example.emtlabs.model.projections.AuthorNameProjection;
import com.example.emtlabs.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> create(String name, String surname, Long countryId);
    Optional<Author> update(Long id,String name,String surname, Long countryId);

    void deleteById(Long id);
    List<AuthorNameProjection> findAllAuthorNames();

}
