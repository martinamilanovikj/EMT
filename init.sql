CREATE TABLE countries (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           continent VARCHAR(255) NOT NULL
);

CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         surname VARCHAR(255) NOT NULL,
                         country_id INT NOT NULL,
                         FOREIGN KEY (country_id) REFERENCES countries(id)
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       date DATE NOT NULL,
                       category VARCHAR(255) NOT NULL,
                       author_id INT NOT NULL,
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

CREATE TABLE wishlists (
                           id SERIAL PRIMARY KEY,
                           date_created TIMESTAMP NOT NULL,
                           user_username VARCHAR(255) NOT NULL,
                           FOREIGN KEY (user_username) REFERENCES users(username)
);

CREATE TABLE wishlist_books (
                                wishlist_id INT NOT NULL,
                                book_id INT NOT NULL,
                                PRIMARY KEY (wishlist_id, book_id),
                                FOREIGN KEY (wishlist_id) REFERENCES wishlists(id),
                                FOREIGN KEY (book_id) REFERENCES books(id)
);
INSERT INTO countries (name, continent) VALUES
                                            ('Country1', 'Continent1'),
                                            ('Country2', 'Continent2'),
                                            ('Country3', 'Continent3');

INSERT INTO authors (name, surname, country_id) VALUES
                                                    ('Name1', 'Surname1', 2),
                                                    ('Name2', 'Surname2', 3),
                                                    ('Name3', 'Surname3', 1);

INSERT INTO books (name, category, author_id, available_copies, date) VALUES
                                                                          ('Book1', 'BIOGRAPHY', 2, 3, '2020-05-08'),
                                                                          ('Book2', 'DRAMA', 3, 6, '2024-03-02'),
                                                                          ('Book3', 'NOVEL', 1, 2, '2025-03-04'),
                                                                          ('Book4', 'FANTASY', 1, 2, '2025-03-04');

INSERT INTO users (username, password, name, surname, role,
                   is_account_non_expired, is_account_non_locked,
                   is_credentials_non_expired, is_enabled)
VALUES
    ('at', 'passwordHashForAt', 'Ana', 'Todorovska', 'ROLE_LIBRARIAN',
     true, true, true, true),
    ('user', 'passwordHashForUser', 'User', 'User', 'ROLE_USER',
     true, true, true, true);

INSERT INTO wishlists (date_created, user_username) VALUES
    (NOW(), 'user');

INSERT INTO wishlist_books (wishlist_id, book_id) VALUES
                                                      (1, 1),
                                                      (1, 2);
CREATE MATERIALIZED VIEW books_per_author AS
SELECT
    a.id AS author_id,
    a.name AS author_name,
    a.surname AS author_surname,
    COUNT(b.id) AS total_books
FROM authors a
         LEFT JOIN books b ON b.author_id = a.id
GROUP BY a.id, a.name, a.surname;


CREATE MATERIALIZED VIEW authors_per_country AS
SELECT
    c.id AS country_id,
    c.name AS country_name,
    COUNT(a.id) AS total_authors
FROM countries c
         LEFT JOIN authors a ON a.country_id = c.id
GROUP BY c.id, c.name;

REFRESH MATERIALIZED VIEW books_per_author;
REFRESH MATERIALIZED VIEW authors_per_country;
