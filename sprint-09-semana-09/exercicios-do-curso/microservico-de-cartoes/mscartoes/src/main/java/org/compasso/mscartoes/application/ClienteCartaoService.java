package org.compasso.mscartoes.application;

import lombok.RequiredArgsConstructor;
import org.compasso.mscartoes.domain.ClienteCartao;
import org.compasso.mscartoes.infra.repository.ClienteCartaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository clienteCartaoRepository;

    public List<ClienteCartao> listCartoesByCpf(String cpf) {
        return clienteCartaoRepository.findByCpf(cpf);
    }
}
