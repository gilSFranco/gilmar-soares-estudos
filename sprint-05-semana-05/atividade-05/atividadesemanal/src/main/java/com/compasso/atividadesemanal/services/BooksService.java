package com.compasso.atividadesemanal.services;

import com.compasso.atividadesemanal.domain.Books;
import com.compasso.atividadesemanal.dto.BooksDTO;
import com.compasso.atividadesemanal.repository.BooksRepository;
import com.compasso.atividadesemanal.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BooksRepository repository;

    public List<Books> findAll() {
        return repository.findAll();
    }

    public Books findById(String id) {
        Optional<Books> book = repository.findById(id);
        return book.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public Books insert(Books object) {
        return repository.save(object);
    }

    public void delete(String id) {
        Books book = findById(id);
        repository.delete(book);
    }

    public Books fromDTO(BooksDTO object) {
        return new Books(object.getId(), object.getTitle(), object.getAuthor(), object.getYear(), object.getGender());
    }
}
