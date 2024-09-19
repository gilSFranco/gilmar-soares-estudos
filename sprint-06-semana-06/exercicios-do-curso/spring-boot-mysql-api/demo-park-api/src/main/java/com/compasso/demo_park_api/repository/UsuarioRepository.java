package com.compasso.demo_park_api.repository;

import com.compasso.demo_park_api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query(
            "SELECT usuario.role FROM Usuario usuario WHERE usuario.username LIKE :username"
    )
    Usuario.Role findRoleByUsername(String username);
}
