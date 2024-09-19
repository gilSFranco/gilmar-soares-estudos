package com.compasso.demo_park_api.web.controller;

import com.compasso.demo_park_api.jwt.JwtToken;
import com.compasso.demo_park_api.jwt.JwtUserDetailsService;
import com.compasso.demo_park_api.web.dto.UsuarioLoginDTO;
import com.compasso.demo_park_api.web.dto.UsuarioResponseDTO;
import com.compasso.demo_park_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Autenticação",
    description = "Recurso para proceder com a autenticação na API"
)
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {

    private final JwtUserDetailsService detailsService;
    private final AuthenticationManager authenticationManager;

    @Operation(
            summary = "Autenticar na API",
            description = "Recurso de autenticação na API",
            security = @SecurityRequirement(
                    name = "security"
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticação realizada com sucesso e retorno de um bearer token",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Credenciais inválidas",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Campos(s) inválido(s)",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDTO usuarioDto, HttpServletRequest request) {
        log.info("Processo de autenticação pelo login '{}'", usuarioDto.getUsername());

        try{

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(usuarioDto.getUsername(), usuarioDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(usuarioDto.getUsername());
            return ResponseEntity.ok(token);

        } catch (AuthenticationException e) {
            log.warn("Bad Credentials from username '{}'", usuarioDto.getUsername());
        }

        return ResponseEntity.badRequest().body(
                new ErrorMessage(
                        request,
                        HttpStatus.BAD_REQUEST,
                        "Credenciais Inválidas"
                )
        );
    }
}
