package com.compasso.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosCliente {
    private Long id;
    private String nome;
    private Integer idade;
}
