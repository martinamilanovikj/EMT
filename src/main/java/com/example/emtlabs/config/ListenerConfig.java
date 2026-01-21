package com.example.emtlabs.config;

import com.example.emtlabs.listeners.AuthorEntityListener;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ListenerConfig {

    private final JdbcTemplate jdbcTemplate;

    public ListenerConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        AuthorEntityListener.setJdbcTemplate(jdbcTemplate);
    }
}
