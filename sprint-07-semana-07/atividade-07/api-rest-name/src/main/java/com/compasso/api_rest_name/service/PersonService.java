package com.compasso.api_rest_name.service;

import com.compasso.api_rest_name.dto.PersonDTO;
import com.compasso.api_rest_name.dto.PersonResponseDTO;
import com.compasso.api_rest_name.dto.mapper.PersonMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    public PersonResponseDTO verificarEntrada(PersonDTO object) {
        PersonResponseDTO personResponseDTO = PersonMapper.toResponseDto(object);

        if(object.getAge() >= 18) {
            personResponseDTO.setMessage("Entry Allowed");
        } else{
            personResponseDTO.setMessage("Entry Denied");
        }

        return personResponseDTO;
    }
}
