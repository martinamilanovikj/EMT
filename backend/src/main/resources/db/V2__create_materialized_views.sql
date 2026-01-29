DROP MATERIALIZED VIEW IF EXISTS books_per_author CASCADE;
DROP MATERIALIZED VIEW IF EXISTS authors_per_country CASCADE;

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
