package com.compasso.api_rest_name.dto.mapper;

import com.compasso.api_rest_name.dto.PersonDTO;
import com.compasso.api_rest_name.dto.PersonResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonMapper {
    public static PersonResponseDTO toResponseDto(PersonDTO personDto) {
        return new ModelMapper().map(personDto, PersonResponseDTO.class);
    }
}
