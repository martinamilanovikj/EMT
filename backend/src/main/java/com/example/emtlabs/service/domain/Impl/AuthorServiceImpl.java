package com.example.emtlabs.service.domain.Impl;
import com.example.emtlabs.model.domain.Author;
import com.example.emtlabs.model.exceptions.InvalidAuthorId;
import com.example.emtlabs.model.exceptions.InvalidCountryId;
import com.example.emtlabs.model.projections.AuthorNameProjection;
import com.example.emtlabs.repository.AuthorRepository;
import com.example.emtlabs.service.domain.AuthorService;
import com.example.emtlabs.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> create(String name, String surname, Long countryId) {
        return Optional.of(authorRepository.save(new Author(name, surname, countryService.findById(countryId).orElseThrow(InvalidCountryId::new))));
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository.findById(id).orElseThrow(InvalidAuthorId::new));    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Long countryId) {
        Author author = findById(id).orElseThrow(InvalidAuthorId::new);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(countryService.findById(countryId).orElseThrow(InvalidCountryId::new));
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {

        authorRepository.deleteById(id);
    }
    @Override
    public List<AuthorNameProjection> findAllAuthorNames() {
        return authorRepository.findAllAuthorNames();
    }


}


