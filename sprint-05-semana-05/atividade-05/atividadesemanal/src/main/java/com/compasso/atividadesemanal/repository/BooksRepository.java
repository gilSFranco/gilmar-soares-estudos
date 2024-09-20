package com.compasso.atividadesemanal.repository;

import com.compasso.atividadesemanal.domain.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends MongoRepository<Books, String> {
    List<Books> findByAuthorContainingIgnoreCase(String author);
    List<Books> findByYearGreaterThan(Integer year);
}
