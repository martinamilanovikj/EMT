package com.example.emtlabs.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "authors_per_country")
@Data
public class AuthorsPerCountryView {
    @Id
    private Long countryId;
    private String countryName;
    private Long totalAuthors;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getTotalAuthors() {
        return totalAuthors;
    }

    public void setTotalAuthors(Long totalAuthors) {
        this.totalAuthors = totalAuthors;
    }
}

