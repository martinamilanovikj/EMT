package com.example.emt1.service.application;

import com.example.emt1.dto.CreateCountryDto;
import com.example.emt1.dto.DisplayCountryDto;
import com.example.emt1.dto.UpdateCountryDto;


import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> getAllCounties();
    Optional<DisplayCountryDto> getCountryId(Long id);
    Optional<DisplayCountryDto>createCountry(CreateCountryDto createCountryDto);
    Optional<DisplayCountryDto> updateCountry(Long id, UpdateCountryDto updateCountryDto);
    void deleteById(Long id);
}

