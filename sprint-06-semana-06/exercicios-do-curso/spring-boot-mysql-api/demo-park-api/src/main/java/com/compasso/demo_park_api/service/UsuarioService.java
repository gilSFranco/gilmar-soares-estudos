package com.compasso.demo_park_api.service;

import com.compasso.demo_park_api.entity.Usuario;
import com.compasso.demo_park_api.exception.EntityNotFoundException;
import com.compasso.demo_park_api.exception.PasswordInvalidException;
import com.compasso.demo_park_api.exception.UsernameUniqueViolationException;
import com.compasso.demo_park_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario insert(Usuario usuario) {
        try{
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário com o id: %s não encontrado", id))
        );
    }

    @Transactional
    public Usuario updatePassword(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if(!novaSenha.equals(confirmaSenha)) {
            throw new PasswordInvalidException("Nova senha não confere com confirmação de senha!");
        }

        Usuario objeto = findById(id);

        if(!passwordEncoder.matches(senhaAtual, objeto.getPassword())) {
            throw new PasswordInvalidException("Sua senha não confere!");
        }

        objeto.setPassword(passwordEncoder.encode(novaSenha));
        return objeto;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário com o username: %s não encontrado", username))
        );
    }

    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}
