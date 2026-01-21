package com.example.emtlabs.service.application.Impl;

import com.example.emtlabs.dto.CreateCountryDto;
import com.example.emtlabs.dto.DisplayCountryDto;
import com.example.emtlabs.dto.UpdateCountryDto;
import com.example.emtlabs.model.domain.Country;
import com.example.emtlabs.service.application.CountryApplicationService;
import com.example.emtlabs.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> getAllCounties() {
        return countryService.findAll().stream()
                .map(DisplayCountryDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayCountryDto> getCountryId(Long id) {
        return countryService.findById(id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> createCountry(CreateCountryDto createCountryDto) {
        return countryService.create(createCountryDto.name(), createCountryDto.continent())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> updateCountry(Long id, UpdateCountryDto updateCountryDto) {
        Optional<Country> existingCountry = countryService.findById(id);

        if (existingCountry.isPresent()) {
            Country country = existingCountry.get();
            country.setName(updateCountryDto.name());
            country.setContinent(updateCountryDto.continent());

            return countryService.update(id, updateCountryDto.name(), updateCountryDto.continent())
                    .map(updatedCountry -> new DisplayCountryDto(
                            updatedCountry.getId(),
                            updatedCountry.getName(),
                            updatedCountry.getContinent()
                    ));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}