package com.compasso.demo_park_api.service;

import com.compasso.demo_park_api.entity.Vaga;
import com.compasso.demo_park_api.exception.CodigoUniqueViolationException;
import com.compasso.demo_park_api.exception.EntityNotFoundException;
import com.compasso.demo_park_api.exception.VagaDisponivelException;
import com.compasso.demo_park_api.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.compasso.demo_park_api.entity.Vaga.StatusVaga.LIVRE;

@RequiredArgsConstructor
@Service
public class VagaService {

    private final VagaRepository vagaRepository;

    @Transactional
    public Vaga salvar(Vaga vaga) {
        try{
            return vagaRepository.save(vaga);
        } catch (DataIntegrityViolationException e) {
            throw new CodigoUniqueViolationException("Vaga", vaga.getCodigo());
        }
    }

    @Transactional(readOnly = true)
    public Vaga buscarPorCodigo(String codigo) {
        return vagaRepository.findByCodigo(codigo).orElseThrow(
                () -> new EntityNotFoundException("Vaga", codigo)
        );
    }

    @Transactional(readOnly = true)
    public Vaga buscarPorVagaLivre() {
        return vagaRepository.findFirstByStatus(LIVRE).orElseThrow(
                () -> new VagaDisponivelException("Vaga")
        );
    }
}
