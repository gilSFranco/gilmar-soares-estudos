package org.compasso.mscartoes.application;

import lombok.RequiredArgsConstructor;
import org.compasso.mscartoes.application.representation.CartaoSaveRequest;
import org.compasso.mscartoes.application.representation.CartoesPorClienteResponse;
import org.compasso.mscartoes.domain.Cartao;
import org.compasso.mscartoes.domain.ClienteCartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Cartao> cadastra(@RequestBody CartaoSaveRequest request) {
        Cartao cartao = request.toModel();
        Cartao novoCartao = cartaoService.save(cartao);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoCartao);
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        List<Cartao> listaCartoes = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(listaCartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        List<ClienteCartao> listaCartoes = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = listaCartoes.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .toList();

        return ResponseEntity.ok(resultList);
    }
}
