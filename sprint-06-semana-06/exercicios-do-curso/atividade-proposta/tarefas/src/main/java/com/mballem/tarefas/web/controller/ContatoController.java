package com.mballem.tarefas.web.controller;

import com.mballem.internal.entity.Contato;
import com.mballem.internal.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("tarefas")
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping("/contatos")
    public ResponseEntity<Contato> create(@RequestBody Contato contato) {
        Contato objeto = contatoService.save(contato);
        return ResponseEntity.ok().body(objeto);
    }

    @GetMapping("/contatos/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
        Contato objeto = contatoService.getById(id);
        return ResponseEntity.ok().body(objeto);
    }

    @GetMapping("/contatos/nome/{nome}")
    public ResponseEntity<Contato> getContatoByNome(@PathVariable String nome) {
        Contato objeto = contatoService.getContatoByNome(nome);
        return ResponseEntity.ok().body(objeto);
    }

    @GetMapping("/contatos")
    public ResponseEntity<Integer> getQuantidadeDeContatos() {
        int objeto = contatoService.getAll();
        return ResponseEntity.ok().body(objeto);
    }

    @GetMapping("/contatos/data")
    public ResponseEntity<List<Contato>> getContatosByDataNascimento(@RequestParam(value = "data", defaultValue = "") LocalDate dataNascimento) {
        List<Contato> objeto = contatoService.getByDataNascimento(dataNascimento);
        return ResponseEntity.ok().body(objeto);
    }

    @PatchMapping("/contatos/{id}")
    public ResponseEntity<Contato> updateContatoById(@PathVariable Long id, @RequestBody Contato contato) {
        Contato objeto = contatoService.update(id, contato);
        return ResponseEntity.ok().body(objeto);
    }

    @DeleteMapping("/contatos/{id}")
    public String deleteById(@PathVariable Long id) {
        return contatoService.delete(id);
    }
}
