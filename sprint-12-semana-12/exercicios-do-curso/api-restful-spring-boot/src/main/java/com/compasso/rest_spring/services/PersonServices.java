package com.compasso.rest_spring.services;

import com.compasso.rest_spring.controller.PersonController;
import com.compasso.rest_spring.data.vo.v1.PersonVO;
import com.compasso.rest_spring.data.vo.v2.PersonVOV2;
import com.compasso.rest_spring.exceptions.RequiredObjectIsNullException;
import com.compasso.rest_spring.exceptions.ResourceNotFoundException;
import com.compasso.rest_spring.mapper.DozerMapper;
import com.compasso.rest_spring.mapper.custom.PersonMapper;
import com.compasso.rest_spring.model.Person;
import com.compasso.rest_spring.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    public PersonVO findById(Long id) {
        logger.info("Finding one person");

        Person person = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        PersonVO vo = DozerMapper.parseObject(person, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        List<PersonVO> persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(
                        person -> person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel())
                );

        return persons;
    }

    public PersonVO create(PersonVO object) {
        if (object == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one person");

        Person person = DozerMapper.parseObject(object, Person.class);
        PersonVO vo = DozerMapper.parseObject(repository.save(person), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 object) {
        logger.info("Creating one person with V2");

        Person person = mapper.convertVOToEntity(object);
        return mapper.convertEntityToVO(repository.save(person));
    }

    public PersonVO update(PersonVO object) {
        if (object == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person");

        Person person = repository.findById(object.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        person.setFirstName(object.getFirstName());
        person.setLastName(object.getLastName());
        person.setAddress(object.getAddress());
        person.setGender(object.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(person), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    @Transactional
    public PersonVO disablePerson(Long id) {

        logger.info("Disabling one person!");

        repository.disablePerson(id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        Person object = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(object);
    }
}
