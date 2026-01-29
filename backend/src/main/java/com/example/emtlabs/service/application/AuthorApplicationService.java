package com.example.emtlabs.service.application;

import com.example.emtlabs.dto.CreateAuthorDto;
import com.example.emtlabs.dto.DisplayAuthorDto;
import com.example.emtlabs.dto.UpdateAuthorDto;
import com.example.emtlabs.model.projections.AuthorNameProjection;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> getAllAuthors();
    Optional<DisplayAuthorDto> getAuthorById(Long id);
    Optional<DisplayAuthorDto> createAuthor(CreateAuthorDto dto);
    Optional<DisplayAuthorDto> updateAuthor(Long id, UpdateAuthorDto dto);
    void deleteAuthor(Long id);

    List<AuthorNameProjection> getAllAuthorNames();


}
