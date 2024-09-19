package com.compasso.demo_park_api.web.controller;

import com.compasso.demo_park_api.entity.ClienteVaga;
import com.compasso.demo_park_api.jwt.JwtUserDetails;
import com.compasso.demo_park_api.repository.projection.ClienteVagaProjection;
import com.compasso.demo_park_api.service.ClienteService;
import com.compasso.demo_park_api.service.ClienteVagaService;
import com.compasso.demo_park_api.service.EstacionamentoService;
import com.compasso.demo_park_api.service.JasperService;
import com.compasso.demo_park_api.web.dto.ClienteResponseDTO;
import com.compasso.demo_park_api.web.dto.EstacionamentoCreateDTO;
import com.compasso.demo_park_api.web.dto.EstacionamentoResponseDTO;
import com.compasso.demo_park_api.web.dto.PageableDTO;
import com.compasso.demo_park_api.web.dto.mapper.ClienteVagaMapper;
import com.compasso.demo_park_api.web.dto.mapper.PageableMapper;
import com.compasso.demo_park_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@Tag(name = "Estacionamentos", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um estacionamento.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/estacionamentos")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;
    private final ClienteVagaService clienteVagaService;
    private final ClienteService clienteService;
    private final JasperService jasperService;

    @Operation(
            summary = "Operação de check-in",
            description = "Recurso para dar entrada de um veículo no estacionamento. Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(
                    name = "security"
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso criado com sucesso",
                            headers = @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "URL de acesso ao recurso criado"
                            ),
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = EstacionamentoResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description =
                                    "Causas possiveis: <br/>" +
                                            "<br/>" +
                                            "- CPF do cliente não cadastrado no sistema; <br/>" +
                                            "- Nenhuma vaga livre foi encontrada;"
                            ,
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping("/check-in")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstacionamentoResponseDTO> checkIn(@RequestBody EstacionamentoCreateDTO dto) {
        ClienteVaga clienteVaga = ClienteVagaMapper.toClienteVaga(dto);
        estacionamentoService.checkIn(clienteVaga);

        EstacionamentoResponseDTO responseDTO = ClienteVagaMapper.toDto(clienteVaga);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{recibo}")
                .buildAndExpand(clienteVaga.getRecibo())
                .toUri();

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @Operation(
            summary = "Localizar um veiculo estacionado",
            description = "Recurso para retornar um veiculo estacionado. Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(
                    name = "security"
            ),
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "recibo",
                            description = "Número do recibo gerado pelo check-in"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso localizado com sucesso",
                            headers = @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "URL de acesso ao recurso criado"
                            ),
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = EstacionamentoResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Número do recibo não encontrado",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/check-in/{recibo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    public ResponseEntity<EstacionamentoResponseDTO> getByRecibo(@PathVariable String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
        EstacionamentoResponseDTO dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Operação de check-out",
            description = "Recurso para dar saida de um veículo do estacionamento. Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(
                    name = "security"
            ),
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "recibo",
                            description = "Número do recibo gerado pelo check-in"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso atualizado com sucesso",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = EstacionamentoResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Número do recibo inexistente ou o veículo já passou pelo check-out",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PutMapping("/check-out/{recibo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstacionamentoResponseDTO> checkOut(@PathVariable String recibo) {
        ClienteVaga clienteVaga = estacionamentoService.checkOut(recibo);
        EstacionamentoResponseDTO dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Localizar os registros de estacionamento do cliente por CPF",
            description = "Localizar os registros de estacionamento do cliente por CPF. Requisição exige uso de um bearer token.",
            security = @SecurityRequirement(
                    name = "security"
            ),
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "cpf",
                            description = "N° do CPF referente ao cliente a ser consultado",
                            required = true
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "page",
                            content = @Content(
                                    schema = @Schema(
                                            type = "integer",
                                            defaultValue = "0"
                                    )
                            ),
                            description = "Representa a página retornada."
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "size",
                            content = @Content(
                                    schema = @Schema(
                                            type = "integer",
                                            defaultValue = "5"
                                    )
                            ),
                            description = "Representa o total de elementos por página."
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "sort",
                            hidden = true,
                            array = @ArraySchema(
                                    schema = @Schema(
                                            type = "string",
                                            defaultValue = "dataEntrada,asc"
                                    )
                            ),
                            description = "Campo padrão de ordenação 'dataEntrada,asc'."
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso localizado com sucesso",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = EstacionamentoResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping("/cpf/{cpf}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDTO> getAllEstacionamentosPorCpf(
            @PathVariable String cpf,
            @PageableDefault(
                    size = 5,
                    sort = "dataEntrada",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosPorClienteCpf(cpf, pageable);
        PageableDTO dto = PageableMapper.toDTO(projection);

        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Localizar os registros de estacionamento do cliente logado",
            description = "Localizar os registros de estacionamento do cliente logado. Requisição exige uso de um bearer token.",
            security = @SecurityRequirement(
                    name = "security"
            ),
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "page",
                            content = @Content(
                                    schema = @Schema(
                                            type = "integer",
                                            defaultValue = "0"
                                    )
                            ),
                            description = "Representa a página retornada."
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "size",
                            content = @Content(
                                    schema = @Schema(
                                            type = "integer",
                                            defaultValue = "5"
                                    )
                            ),
                            description = "Representa o total de elementos por página."
                    ),
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "sort",
                            hidden = true,
                            array = @ArraySchema(
                                    schema = @Schema(
                                            type = "string",
                                            defaultValue = "dataEntrada,asc"
                                    )
                            ),
                            description = "Campo padrão de ordenação 'dataEntrada,asc'."
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso localizado com sucesso",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = EstacionamentoResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Recurso não permitido ao perfil de ADMIN",
                            content = @Content(
                                    mediaType = "application/json;charset=UTF-8",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PageableDTO> getAllEstacionamentosDoCliente(
            @AuthenticationPrincipal JwtUserDetails user,
            @PageableDefault(
                    size = 5,
                    sort = "dataEntrada",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosPorUsuarioId(user.getId(), pageable);
        PageableDTO dto = PageableMapper.toDTO(projection);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/relatorio")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Void> getRelatorio(HttpServletResponse response, @AuthenticationPrincipal JwtUserDetails user) {
        String cpf = clienteService.buscarPorUsuarioId(user.getId()).getCpf();
        JasperService.addParams("CPF", cpf);

        byte[] bytes = jasperService.gerarPdf();

        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-Disposition", "inline; filename=" + System.currentTimeMillis() + ".pdf");

        try {
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().build();
    }
}
