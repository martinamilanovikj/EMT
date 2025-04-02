package com.example.emt1.service.application;

import com.example.emt1.dto.CreateAuthorDto;
import com.example.emt1.dto.DisplayAuthorDto;
import com.example.emt1.dto.UpdateAuthorDto;


import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> getAllAuthors();
    Optional<DisplayAuthorDto> getAuthorById(Long id);
    Optional<DisplayAuthorDto> createAuthor(CreateAuthorDto dto);
    Optional<DisplayAuthorDto> updateAuthor(Long id, UpdateAuthorDto dto);
    void deleteAuthor(Long id);

}
