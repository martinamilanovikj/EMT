package com.example.emtlabs.repository;

import com.example.emtlabs.model.domain.Author;
import com.example.emtlabs.model.projections.AuthorNameProjection;
import com.example.emtlabs.model.views.AuthorsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a.name AS name, a.surname AS surname FROM Author a")
    List<AuthorNameProjection> findAllAuthorNames();

    @Query(value = "SELECT * FROM authors_per_country", nativeQuery = true)
    List<AuthorsPerCountryView> getAuthorsPerCountry();
}
