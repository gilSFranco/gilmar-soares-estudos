package com.compasso.rest_spring.integrationtests.vo.wrappers;

import java.io.Serializable;
import java.util.List;

import com.compasso.rest_spring.integrationtests.vo.BookVO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookEmbeddedVO implements Serializable {
    
    @JsonProperty("bookVOList")
    private List<BookVO> books;

    public BookEmbeddedVO() {}

    public List<BookVO> getBooks() {
        return books;
    }

    public void setBooks(List<BookVO> books) {
        this.books = books;
    }
}