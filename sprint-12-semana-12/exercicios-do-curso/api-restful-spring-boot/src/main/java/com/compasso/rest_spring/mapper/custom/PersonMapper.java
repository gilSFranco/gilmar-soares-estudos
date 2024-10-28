package com.compasso.rest_spring.mapper.custom;

import com.compasso.rest_spring.data.vo.v2.PersonVOV2;
import com.compasso.rest_spring.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {
    public PersonVOV2 convertEntityToVO(Person person) {
        PersonVOV2 personVO = new PersonVOV2();

        personVO.setId(person.getId());
        personVO.setAddress(person.getAddress());
        personVO.setBirthDay(new Date());
        personVO.setFirstName(person.getFirstName());
        personVO.setLastName(person.getLastName());
        personVO.setGender(person.getGender());

        return personVO;
    }

    public Person convertVOToEntity(PersonVOV2 personVO) {
        Person person = new Person();

        person.setId(personVO.getId());
        person.setAddress(personVO.getAddress());
        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setGender(personVO.getGender());

        return person;
    }
}
