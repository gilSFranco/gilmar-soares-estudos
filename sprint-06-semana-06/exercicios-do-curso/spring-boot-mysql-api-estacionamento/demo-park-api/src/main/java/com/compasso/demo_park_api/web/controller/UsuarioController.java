package com.compasso.demo_park_api.web.controller;

import com.compasso.demo_park_api.entity.Usuario;
import com.compasso.demo_park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario objeto = usuarioService.insert(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(objeto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario objeto = usuarioService.findById(id);
        return ResponseEntity.ok().body(objeto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario objeto = usuarioService.updatePassword(id, usuario.getPassword());
        return ResponseEntity.ok().body(objeto);
    }
}
