package com.compasso.msavaliadorcredito.application;

import com.compasso.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.compasso.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import com.compasso.msavaliadorcredito.application.ex.ErroSolicitacaoCartaoException;
import com.compasso.msavaliadorcredito.domain.model.*;
import com.compasso.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.compasso.msavaliadorcredito.infra.clients.ClienteResourceClient;
import com.compasso.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesResourceClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartoesRendaAte(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            List<CartaoAprovado> listaCartoesAprovados = cartoes.stream().map(
                    cartao -> {
                        DadosCliente dadosCliente = dadosClienteResponse.getBody();

                        BigDecimal limiteBasico = cartao.getLimiteBasico();
                        BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());

                        BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
                        BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                        CartaoAprovado aprovado = new CartaoAprovado();
                        aprovado.setCartao(cartao.getNome());
                        aprovado.setBandeira(cartao.getBandeira());
                        aprovado.setLimiteAprovado(limiteAprovado);

                        return aprovado;
                    }
            ).toList();

            return new RetornoAvaliacaoCliente(listaCartoesAprovados);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();

            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }

            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) {
        try{
            emissaoCartaoPublisher.solicitarCartao(dados);
            String protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }
    }
}
