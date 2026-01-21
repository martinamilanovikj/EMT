package com.example.emtlabs.service.application;

import com.example.emtlabs.dto.CreateCountryDto;
import com.example.emtlabs.dto.DisplayCountryDto;
import com.example.emtlabs.dto.UpdateCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> getAllCounties();
    Optional<DisplayCountryDto> getCountryId(Long id);
    Optional<DisplayCountryDto>createCountry(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> updateCountry(Long id, UpdateCountryDto updateCountryDto);
    void deleteById(Long id);
}

