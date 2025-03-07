package com.compasso.rest_spring.unittests.mapper.mockito.services;

import com.compasso.rest_spring.data.vo.v1.BookVO;
import com.compasso.rest_spring.exceptions.RequiredObjectIsNullException;
import com.compasso.rest_spring.model.Book;
import com.compasso.rest_spring.repositories.BookRepository;
import com.compasso.rest_spring.services.BookServices;
import com.compasso.rest_spring.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    private BookRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/v1/books/1>;rel=\"self\"]"));

        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(25D, result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testCreate() {
        Book book = input.mockEntity(1);

        Book persisted = book;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setId(1L);

        when(repository.save(book)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/v1/books/1>;rel=\"self\"]"));

        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(25D, result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        Book persisted = book;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/v1/books/1>;rel=\"self\"]"));

        assertEquals("Some Author1", result.getAuthor());
        assertEquals("Some Title1", result.getTitle());
        assertEquals(25D, result.getPrice());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete() {
        Book book = input.mockEntity(1);
        book.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        service.findById(1L);

        service.delete(1L);
    }

    @Test
    void testFindAll() {
        List<Book> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var books = service.findAll();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertTrue(bookOne.toString().contains("links: [</api/v1/books/1>;rel=\"self\"]"));

        assertEquals("Some Author1", bookOne.getAuthor());
        assertEquals("Some Title1", bookOne.getTitle());
        assertEquals(25D, bookOne.getPrice());
        assertNotNull(bookOne.getLaunchDate());

        var bookFour = books.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertTrue(bookFour.toString().contains("links: [</api/v1/books/4>;rel=\"self\"]"));

        assertEquals("Some Author4", bookFour.getAuthor());
        assertEquals("Some Title4", bookFour.getTitle());
        assertEquals(25D, bookFour.getPrice());
        assertNotNull(bookFour.getLaunchDate());

        var bookSeven = books.get(7);

        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());

        assertTrue(bookSeven.toString().contains("links: [</api/v1/books/7>;rel=\"self\"]"));

        assertEquals("Some Author7", bookSeven.getAuthor());
        assertEquals("Some Title7", bookSeven.getTitle());
        assertEquals(25D, bookSeven.getPrice());
        assertNotNull(bookSeven.getLaunchDate());
    }
}
