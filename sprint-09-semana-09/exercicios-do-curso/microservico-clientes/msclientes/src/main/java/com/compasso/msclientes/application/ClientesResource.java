package com.compasso.msclientes.application;

import com.compasso.msclientes.application.representation.ClienteSaveRequest;
import com.compasso.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClientesResource {
    private final ClienteService clienteService;

    @GetMapping
    public String status() {
        log.info("Obtendo o status do microservice de clientes.");
        return "OK";
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        Cliente novoCliente = clienteService.save(cliente);

        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(novoCliente.getCpf()).toUri();
        return ResponseEntity.created(headerLocation).body(novoCliente);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<?> dadosCliente(@RequestParam("cpf") String cpf) {
        Optional<Cliente> cliente = clienteService.getByCpf(cpf);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cliente.get());
    }
}
