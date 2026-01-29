CREATE TABLE countries (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           continent VARCHAR(255) NOT NULL
);

CREATE TABLE authors (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         surname VARCHAR(255) NOT NULL,
                         country_id BIGINT NOT NULL,
                         FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE TABLE books (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       date DATE NOT NULL,
                       category VARCHAR(255) NOT NULL,
                       author_id BIGINT NOT NULL,
                       available_copies INT NOT NULL,
                       FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE users (
                       username VARCHAR(255) PRIMARY KEY,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(255) NOT NULL,
                       surname VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL,
                       is_account_non_expired BOOLEAN NOT NULL,
                       is_account_non_locked BOOLEAN NOT NULL,
                       is_credentials_non_expired BOOLEAN NOT NULL,
                       is_enabled BOOLEAN NOT NULL
);
