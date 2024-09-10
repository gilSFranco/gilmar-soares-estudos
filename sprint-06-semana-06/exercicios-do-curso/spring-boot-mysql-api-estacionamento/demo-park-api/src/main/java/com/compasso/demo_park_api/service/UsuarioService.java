package com.compasso.demo_park_api.service;

import com.compasso.demo_park_api.entity.Usuario;
import com.compasso.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario insert(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(
                () -> new RuntimeException("Object not found")
        );
    }

    @Transactional
    public Usuario updatePassword(Long id, String newPassword) {
        Usuario objeto = findById(id);
        objeto.setPassword(newPassword);
        return objeto;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}
