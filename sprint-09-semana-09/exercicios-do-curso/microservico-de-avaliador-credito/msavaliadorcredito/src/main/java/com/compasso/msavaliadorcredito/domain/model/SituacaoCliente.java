package com.compasso.msavaliadorcredito.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SituacaoCliente {
    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;
}
