package com.example.emtlabs.repository;

import com.example.emtlabs.dto.BooksPerAuthorView;
import com.example.emtlabs.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {

    List<Book> findTop10ByOrderByDateDesc();


    @Query("SELECT new com.example.emtlabs.dto.BooksPerAuthorView(b.author.id, b.author.name, b.author.surname, COUNT(b)) " +
            "FROM Book b " +
            "GROUP BY b.author.id, b.author.name, b.author.surname")
    List<BooksPerAuthorView> getBooksPerAuthor();

}
