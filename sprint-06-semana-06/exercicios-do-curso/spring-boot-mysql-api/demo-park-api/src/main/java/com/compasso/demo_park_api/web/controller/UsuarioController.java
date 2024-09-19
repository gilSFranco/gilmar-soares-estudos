package com.compasso.demo_park_api.web.controller;

import com.compasso.demo_park_api.entity.Usuario;
import com.compasso.demo_park_api.service.UsuarioService;
import com.compasso.demo_park_api.web.dto.UsuarioCreateDTO;
import com.compasso.demo_park_api.web.dto.UsuarioResponseDTO;
import com.compasso.demo_park_api.web.dto.UsuarioSenhaDTO;
import com.compasso.demo_park_api.web.dto.mapper.UsuarioMapper;
import com.compasso.demo_park_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um usuário.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(
            summary = "Criar um novo usuário",
            description = "Recurso para criar um novo usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso criado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Usuário e-mail já cadastrado no sistema",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Recurso não processado por dados de entrada válidos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioCreateDTO createDTO) {
        Usuario objeto = usuarioService.insert(UsuarioMapper.toUsuario(createDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(objeto));
    }

    @Operation(
            summary = "Recuperar um usuário pelo id",
            description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN|CLIENTE",
            security = @SecurityRequirement(
                    name = "security"
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('CLIENTE') AND #id == authentication.principal.id)")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        Usuario objeto = usuarioService.findById(id);
        return ResponseEntity.ok().body(UsuarioMapper.toDto(objeto));
    }

    @Operation(
            summary = "Atualizar senha",
            description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN|CLIENTE",
            security = @SecurityRequirement(
                    name = "security"
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Senha atualizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Senha não confere",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Campos inválidos ou mal formatados",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE') AND (#id == authentication.principal.id)")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UsuarioSenhaDTO senhaDTO) {
        usuarioService.updatePassword(id, senhaDTO.getSenhaAtual(), senhaDTO.getNovaSenha(), senhaDTO.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Recuperar todos os usuários cadastrados",
            description = "Requisição exige um Bearer Token. Acesso restrito a ADMIN",
            security = @SecurityRequirement(
                    name = "security"
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = UsuarioResponseDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        List<Usuario> objetos = usuarioService.findAll();
        return ResponseEntity.ok().body(UsuarioMapper.toListDto(objetos));
    }
}
