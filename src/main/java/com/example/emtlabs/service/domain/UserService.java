package com.example.emtlabs.service.domain;

import com.example.emtlabs.model.domain.User;
import com.example.emtlabs.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    User login(String username, String password);
}
