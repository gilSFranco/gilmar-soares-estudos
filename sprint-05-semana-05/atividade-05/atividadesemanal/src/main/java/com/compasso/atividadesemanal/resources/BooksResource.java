package com.compasso.atividadesemanal.resources;

import com.compasso.atividadesemanal.domain.Books;
import com.compasso.atividadesemanal.dto.BooksDTO;
import com.compasso.atividadesemanal.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BooksResource {

    @Autowired
    private BooksService service;

    @GetMapping
    public ResponseEntity<List<BooksDTO>> findAll() {
        List<Books> list = service.findAll();
        List<BooksDTO> listDto = list.stream().map(x -> new BooksDTO(x)).toList();

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BooksDTO> findById(@PathVariable String id) {
        Books object = service.findById(id);
        return ResponseEntity.ok().body(new BooksDTO(object));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody BooksDTO objectDto) {
        Books object = service.fromDTO(objectDto);
        object = service.insert(object);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
