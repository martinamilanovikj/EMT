package com.example.emtlabs.model.domain;

import com.example.emtlabs.model.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private LocalDate date;
  @Enumerated(EnumType.STRING)
  Category category;
  @ManyToOne
  Author author;
  private Integer availableCopies;

    public Book(String name, Category category, Author author, LocalDate date, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.date = date;
        this.availableCopies = availableCopies;
    }
    public Book(){

    }
    public void markAsRented() {
        if (this.availableCopies > 0) {
            this.availableCopies--;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
