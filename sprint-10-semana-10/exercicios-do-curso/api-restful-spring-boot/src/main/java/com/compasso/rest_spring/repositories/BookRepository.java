package com.compasso.rest_spring.repositories;

import com.compasso.rest_spring.model.Book;
import com.compasso.rest_spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
