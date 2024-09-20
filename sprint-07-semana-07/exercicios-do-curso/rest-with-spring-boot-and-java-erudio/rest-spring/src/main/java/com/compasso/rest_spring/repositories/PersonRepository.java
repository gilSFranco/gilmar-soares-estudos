package com.compasso.rest_spring.repositories;

import com.compasso.rest_spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
