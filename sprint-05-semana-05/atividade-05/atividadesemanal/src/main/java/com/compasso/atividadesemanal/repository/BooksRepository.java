package com.compasso.atividadesemanal.repository;

import com.compasso.atividadesemanal.domain.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends MongoRepository<Books, String> {
}
