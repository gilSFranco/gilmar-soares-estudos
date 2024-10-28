package com.compasso.msclientes.application.representation;

import com.compasso.msclientes.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSaveRequest {
    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente toModel() {
        return new Cliente(
               cpf,
               nome,
               idade
        );
    }
}
