package com.compasso.atividadesemanal.resources;

import com.compasso.atividadesemanal.domain.Books;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BooksResource {

    @GetMapping
    public ResponseEntity<List<Books>> findAll() {
        Books book1 = new Books("1", "1984", "George Orwell", 1949, "Ficção Científica");
        Books book2 = new Books("1", "1984", "George Orwell", 1949, "Ficção Científica");

        List<Books> list = new ArrayList<Books>();
        list.addAll(Arrays.asList(book1, book2));

        return ResponseEntity.ok().body(list);
    }
}
