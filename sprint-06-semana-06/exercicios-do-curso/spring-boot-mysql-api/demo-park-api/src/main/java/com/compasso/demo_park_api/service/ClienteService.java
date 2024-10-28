package com.compasso.demo_park_api.service;

import com.compasso.demo_park_api.entity.Cliente;
import com.compasso.demo_park_api.exception.ClienteCpfNotFoundException;
import com.compasso.demo_park_api.exception.ClienteIdNotFoundException;
import com.compasso.demo_park_api.exception.CpfUniqueViolationException;
import com.compasso.demo_park_api.repository.ClienteRepository;
import com.compasso.demo_park_api.repository.projection.ClienteProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        try{
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new CpfUniqueViolationException(
                    String.format("CPF '%s' não pode ser cadastrado, já existe no sistema ", cliente.getCpf())
            );
        }
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ClienteIdNotFoundException("Cliente", id)
        );
    }

    @Transactional(readOnly = true)
    public Page<ClienteProjection> buscarTodos(Pageable pageable) {
        return clienteRepository.findAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorUsuarioId(Long id) {
        return clienteRepository.findByUsuarioId(id);
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(
                () -> new ClienteCpfNotFoundException("Cliente", cpf)
        );
    }
}
