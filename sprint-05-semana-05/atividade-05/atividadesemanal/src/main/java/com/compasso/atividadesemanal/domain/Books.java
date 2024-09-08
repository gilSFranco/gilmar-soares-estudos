package com.compasso.atividadesemanal.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
public class Books implements Serializable {

    @Id
    private String id;
    private String title;
    private String author;
    private Integer year;
    private String gender;

    public Books() {
    }

    public Books(String id, String title, String author, Integer year, String gender) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(id, books.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
