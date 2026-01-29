package com.example.emtlabs.dto;

import com.example.emtlabs.model.domain.User;
import com.example.emtlabs.model.enumerations.Role;
import com.example.emtlabs.repository.UserRepository;

import java.util.List;

public record DisplayUserDto(String username, String name, String surname, Role role) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}