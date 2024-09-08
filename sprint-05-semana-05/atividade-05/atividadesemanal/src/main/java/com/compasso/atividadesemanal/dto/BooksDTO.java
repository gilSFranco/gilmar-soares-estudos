package com.compasso.atividadesemanal.dto;

import com.compasso.atividadesemanal.domain.Books;

import java.io.Serializable;

public class BooksDTO implements Serializable {
    private String id;
    private String title;
    private String author;
    private Integer year;
    private String gender;

    public BooksDTO() {
    }

    public BooksDTO(Books object) {
        id = object.getId();
        title = object.getTitle();
        author = object.getAuthor();
        year = object.getYear();
        gender = object.getGender();
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
}
