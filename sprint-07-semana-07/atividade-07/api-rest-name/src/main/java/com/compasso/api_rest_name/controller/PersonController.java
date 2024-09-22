package com.compasso.api_rest_name.controller;

import com.compasso.api_rest_name.dto.PersonDTO;
import com.compasso.api_rest_name.dto.PersonResponseDTO;
import com.compasso.api_rest_name.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/entradas")
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponseDTO> verificarEntrada(@RequestBody @Valid PersonDTO object) {
        PersonResponseDTO response = personService.verificarEntrada(object);
        return ResponseEntity.ok().body(response);
    }
}
