package com.example.emtlabs.service.application;

import com.example.emtlabs.dto.CreateUserDto;
import com.example.emtlabs.dto.DisplayUserDto;
import com.example.emtlabs.dto.LoginResponseDto;
import com.example.emtlabs.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    Object getAllUsers();
}



