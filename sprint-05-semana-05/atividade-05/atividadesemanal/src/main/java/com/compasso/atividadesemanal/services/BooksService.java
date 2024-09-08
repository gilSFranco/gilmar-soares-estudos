package com.compasso.atividadesemanal.services;

import com.compasso.atividadesemanal.domain.Books;
import com.compasso.atividadesemanal.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BooksRepository repository;

    public List<Books> findAll() {
        return repository.findAll();
    }

}
