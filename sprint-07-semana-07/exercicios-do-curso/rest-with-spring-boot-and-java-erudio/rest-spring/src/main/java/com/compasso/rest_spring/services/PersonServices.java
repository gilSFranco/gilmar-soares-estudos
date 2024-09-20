package com.compasso.rest_spring.services;

import com.compasso.rest_spring.exceptions.ResourceNotFoundException;
import com.compasso.rest_spring.model.Person;
import com.compasso.rest_spring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public Person findById(Long id) {
        logger.info("Finding one person");

        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );
    }

    public List<Person> findAll() {
        logger.info("Finding all people");
        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person");

        Person object = repository.findById(person.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        object.setFirstName(person.getFirstName());
        object.setLastName(person.getLastName());
        object.setAddress(person.getAddress());
        object.setGender(person.getGender());

        return repository.save(object);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        Person object = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(object);
    }
}
