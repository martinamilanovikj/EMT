CREATE MATERIALIZED VIEW IF NOT EXISTS books_per_author AS
SELECT a.id AS author_id,
       a.name,
       a.surname,
       COUNT(b.id) AS book_count
FROM authors a
         LEFT JOIN books b ON a.id = b.author_id
GROUP BY a.id;


CREATE UNIQUE INDEX idx_books_per_author ON books_per_author(author_id);

REFRESH MATERIALIZED VIEW books_per_author;

select * from books_per_author;
