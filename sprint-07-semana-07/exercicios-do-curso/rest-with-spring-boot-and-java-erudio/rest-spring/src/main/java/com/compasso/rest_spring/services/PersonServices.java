package com.compasso.rest_spring.services;

import com.compasso.rest_spring.data.vo.v1.PersonVO;
import com.compasso.rest_spring.data.vo.v2.PersonVOV2;
import com.compasso.rest_spring.exceptions.ResourceNotFoundException;
import com.compasso.rest_spring.mapper.DozerMapper;
import com.compasso.rest_spring.mapper.custom.PersonMapper;
import com.compasso.rest_spring.model.Person;
import com.compasso.rest_spring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        return DozerMapper.parseObject(person, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people");
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO object) {
        logger.info("Creating one person");

        Person person = DozerMapper.parseObject(object, Person.class);
        return DozerMapper.parseObject(repository.save(person), PersonVO.class);
    }

    public PersonVOV2 createV2(PersonVOV2 object) {
        logger.info("Creating one person with V2");

        Person person = mapper.convertVOToEntity(object);
        return mapper.convertEntityToVO(repository.save(person));
    }

    public PersonVO update(PersonVO object) {
        logger.info("Updating one person");

        Person person = repository.findById(object.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        person.setFirstName(object.getFirstName());
        person.setLastName(object.getLastName());
        person.setAddress(object.getAddress());
        person.setGender(object.getGender());

        return DozerMapper.parseObject(repository.save(person), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        Person object = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        repository.delete(object);
    }
}
