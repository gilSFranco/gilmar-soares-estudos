package com.compasso.atividadesemanal.resources;

import com.compasso.atividadesemanal.domain.Books;
import com.compasso.atividadesemanal.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BooksResource {

    @Autowired
    private BooksService service;

    @GetMapping
    public ResponseEntity<List<Books>> findAll() {
        List<Books> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
