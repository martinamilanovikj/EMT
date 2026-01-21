package com.example.emtlabs.dto;

import com.example.emtlabs.model.domain.User;
import com.example.emtlabs.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    /*
        todo: add repeat password logic
     */
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

