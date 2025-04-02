package com.example.emt1.service.application;

import com.example.emt1.dto.CreateUserDto;
import com.example.emt1.dto.DisplayUserDto;
import com.example.emt1.dto.LoginUserDto;


import java.util.Optional;
import java.util.List;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}



