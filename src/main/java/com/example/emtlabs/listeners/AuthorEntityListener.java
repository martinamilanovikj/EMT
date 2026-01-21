package com.example.emtlabs.listeners;

import jakarta.persistence.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorEntityListener {

    private static JdbcTemplate jdbcTemplate;

    public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        AuthorEntityListener.jdbcTemplate = jdbcTemplate;
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    public void refreshView(Object o) {
//        if (jdbcTemplate != null) {
//            jdbcTemplate.execute("REFRESH MATERIALIZED VIEW authors_per_country");
//        }
    }
}
