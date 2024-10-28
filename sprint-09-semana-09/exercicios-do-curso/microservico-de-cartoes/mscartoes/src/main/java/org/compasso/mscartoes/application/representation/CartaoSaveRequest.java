package org.compasso.mscartoes.application.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.compasso.mscartoes.domain.BandeiraCartao;
import org.compasso.mscartoes.domain.Cartao;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel() {
        return new Cartao(
                nome,
                bandeira,
                renda,
                limite
        );
    }
}
