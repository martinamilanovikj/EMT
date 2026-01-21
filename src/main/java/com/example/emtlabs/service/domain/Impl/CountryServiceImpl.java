package com.example.emtlabs.service.domain.Impl;

import com.example.emtlabs.model.domain.Country;
import com.example.emtlabs.model.exceptions.InvalidCountryId;
import com.example.emtlabs.repository.CountryRepository;
import com.example.emtlabs.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = findById(id).orElseThrow(InvalidCountryId::new);
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        return Optional.of(this.countryRepository.save(new Country(name, continent)));
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(this.countryRepository.findById(id).orElseThrow(InvalidCountryId::new));    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}