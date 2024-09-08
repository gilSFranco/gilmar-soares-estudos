package com.compasso.atividadesemanal.resources;

import com.compasso.atividadesemanal.domain.Books;
import com.compasso.atividadesemanal.dto.BooksDTO;
import com.compasso.atividadesemanal.resources.util.URL;
import com.compasso.atividadesemanal.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BooksDTO> update(@PathVariable String id, @RequestBody BooksDTO objectDto) {
        Books object = service.fromDTO(objectDto);
        object.setId(id);
        object = service.update(object);
        return ResponseEntity.ok().body(new BooksDTO(object));
    }

    @GetMapping(value = "/authorsearch")
    public ResponseEntity<List<BooksDTO>> findByAuthor(@RequestParam(value = "text", defaultValue = "") String author) {
        author = URL.decodeParam(author);
        List<Books> list = service.findByAuthor(author);
        List<BooksDTO> listDto = list.stream().map(x -> new BooksDTO(x)).toList();

        return ResponseEntity.ok().body(listDto);
    }
}
