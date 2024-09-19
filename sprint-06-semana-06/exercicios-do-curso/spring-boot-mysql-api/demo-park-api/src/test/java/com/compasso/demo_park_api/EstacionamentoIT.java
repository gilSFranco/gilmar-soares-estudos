package com.compasso.demo_park_api;

import com.compasso.demo_park_api.web.dto.EstacionamentoCreateDTO;
import com.compasso.demo_park_api.web.dto.PageableDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/estacionamentos/estacionamentos-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/estacionamentos/estacionamentos-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EstacionamentoIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void criarCheckIn_ComDadosValidos_RetornarCreatedAndLocationComStatus201() {
        EstacionamentoCreateDTO createDTO = EstacionamentoCreateDTO.builder()
                .placa("WER-1111")
                .marca("FIAT")
                .modelo("PALIO 1.0")
                .cor("AZUL")
                .clienteCpf("01606396030")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .bodyValue(createDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists(HttpHeaders.LOCATION)
                .expectBody()
                .jsonPath("placa").isEqualTo("WER-1111")
                .jsonPath("marca").isEqualTo("FIAT")
                .jsonPath("modelo").isEqualTo("PALIO 1.0")
                .jsonPath("cor").isEqualTo("AZUL")
                .jsonPath("clienteCpf").isEqualTo("01606396030")
                .jsonPath("recibo").exists()
                .jsonPath("dataEntrada").exists()
                .jsonPath("vagaCodigo").exists();
    }

    @Test
    public void criarCheckIn_ComRoleCliente_RetornarErrorStatus403() {
        EstacionamentoCreateDTO createDTO = EstacionamentoCreateDTO.builder()
                .placa("WER-1111")
                .marca("FIAT")
                .modelo("PALIO 1.0")
                .cor("AZUL")
                .clienteCpf("01606396030")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com.br", "123456"))
                .bodyValue(createDTO)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("status").isEqualTo("403")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");
    }

    @Test
    public void criarCheckIn_ComCpfInexistente_RetornarErrorStatus404() {
        EstacionamentoCreateDTO createDTO = EstacionamentoCreateDTO.builder()
                .placa("WER-1111")
                .marca("FIAT")
                .modelo("PALIO 1.0")
                .cor("AZUL")
                .clienteCpf("74946374000")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .bodyValue(createDTO)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");
    }

    @Sql(scripts = "/sql/estacionamentos/estacionamentos-insert-vagas-ocupadas.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/estacionamentos/estacionamentos-delete-vagas-ocupadas.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void criarCheckIn_ComVagasOcupadas_RetornarErrorStatus404() {
        EstacionamentoCreateDTO createDTO = EstacionamentoCreateDTO.builder()
                .placa("WER-1111")
                .marca("FIAT")
                .modelo("PALIO 1.0")
                .cor("AZUL")
                .clienteCpf("22113796058")
                .build();

        testClient
                .post()
                .uri("/api/v1/estacionamentos/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .bodyValue(createDTO)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in")
                .jsonPath("method").isEqualTo("POST");
    }

    @Test
    public void buscarCheckIn_ComPerfilAdmin_RetornarDadosStatus200() {
        testClient
                .get()
                .uri("/api/v1/estacionamentos/check-in/{recibo}", "20240916-112000")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("placa").isEqualTo("FIT-1020")
                .jsonPath("marca").isEqualTo("FIAT")
                .jsonPath("modelo").isEqualTo("PALIO")
                .jsonPath("cor").isEqualTo("VERDE")
                .jsonPath("clienteCpf").isEqualTo("54837862039")
                .jsonPath("recibo").isEqualTo("20240916-112000")
                .jsonPath("dataEntrada").isEqualTo("2024-09-14 11:20:00")
                .jsonPath("vagaCodigo").isEqualTo("A-01");
    }

    @Test
    public void buscarCheckIn_ComPerfilCliente_RetornarDadosStatus200() {
        testClient
                .get()
                .uri("/api/v1/estacionamentos/check-in/{recibo}", "20240916-112000")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bob@email.com.br", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("placa").isEqualTo("FIT-1020")
                .jsonPath("marca").isEqualTo("FIAT")
                .jsonPath("modelo").isEqualTo("PALIO")
                .jsonPath("cor").isEqualTo("VERDE")
                .jsonPath("clienteCpf").isEqualTo("54837862039")
                .jsonPath("recibo").isEqualTo("20240916-112000")
                .jsonPath("dataEntrada").isEqualTo("2024-09-14 11:20:00")
                .jsonPath("vagaCodigo").isEqualTo("A-01");
    }

    @Test
    public void buscarCheckIn_ComReciboInexistente_RetornarErrorStatus404() {
        testClient
                .get()
                .uri("/api/v1/estacionamentos/check-in/{recibo}", "20240916-999999")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bob@email.com.br", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-in/20240916-999999")
                .jsonPath("method").isEqualTo("GET");
    }

    @Test
    public void criarCheckOut_ComReciboExistente_RetornarSucesso() {
        testClient
                .put()
                .uri("/api/v1/estacionamentos/check-out/{recibo}", "20240916-112000")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("placa").isEqualTo("FIT-1020")
                .jsonPath("marca").isEqualTo("FIAT")
                .jsonPath("modelo").isEqualTo("PALIO")
                .jsonPath("cor").isEqualTo("VERDE")
                .jsonPath("clienteCpf").isEqualTo("54837862039")
                .jsonPath("vagaCodigo").isEqualTo("A-01")
                .jsonPath("recibo").isEqualTo("20240916-112000")
                .jsonPath("dataEntrada").exists()
                .jsonPath("dataSaida").exists()
                .jsonPath("valor").exists()
                .jsonPath("desconto").exists();
    }

    @Test
    public void criarCheckOut_ComReciboInexistente_RetornarErrorStatus404() {
        testClient
                .put()
                .uri("/api/v1/estacionamentos/check-out/{recibo}", "20240916-000000")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("status").isEqualTo("404")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-out/20240916-000000")
                .jsonPath("method").isEqualTo("PUT");
    }

    @Test
    public void criarCheckOut_ComRoleCliente_RetornarErrorStatus403() {
        testClient
                .put()
                .uri("/api/v1/estacionamentos/check-out/{recibo}", "20240916-112000")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com.br", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("status").isEqualTo("403")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/check-out/20240916-112000")
                .jsonPath("method").isEqualTo("PUT");
    }

    @Test
    public void buscarEstacionamentos_PorClienteCpf_RetornarSucesso() {
         PageableDTO responseBody = testClient
                    .get()
                    .uri("/api/v1/estacionamentos/cpf/{cpf}?size=1&page=0", "54837862039")
                    .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(PageableDTO.class)
                    .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getContent().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.getTotalPages()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(responseBody.getNumber()).isEqualTo(0);
        org.assertj.core.api.Assertions.assertThat(responseBody.getSize()).isEqualTo(1);

        responseBody = testClient
                .get()
                .uri("/api/v1/estacionamentos/cpf/{cpf}?size=1&page=1", "54837862039")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PageableDTO.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getContent().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.getTotalPages()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(responseBody.getNumber()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.getSize()).isEqualTo(1);
    }

    @Test
    public void buscarEstacionamentos_PorClienteCpfComPerfilCliente_RetornarErrorStatus403() {
        testClient
                .get()
                .uri("/api/v1/estacionamentos/cpf/{cpf}", "54837862039")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com.br", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("status").isEqualTo("403")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos/cpf/54837862039")
                .jsonPath("method").isEqualTo("GET");
    }

    @Test
    public void buscarEstacionamentos_DoClienteLogado_RetornarSucesso() {
        PageableDTO responseBody = testClient
                .get()
                .uri("/api/v1/estacionamentos?size=1&page=0")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bob@email.com.br", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PageableDTO.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getContent().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.getTotalPages()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(responseBody.getNumber()).isEqualTo(0);
        org.assertj.core.api.Assertions.assertThat(responseBody.getSize()).isEqualTo(1);

        responseBody = testClient
                .get()
                .uri("/api/v1/estacionamentos?size=1&page=1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bob@email.com.br", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(PageableDTO.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getContent().size()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.getTotalPages()).isEqualTo(2);
        org.assertj.core.api.Assertions.assertThat(responseBody.getNumber()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.getSize()).isEqualTo(1);
    }

    @Test
    public void buscarEstacionamentos_DoClienteLogadoComPerfilAdmin_RetornarErrorStatus403() {
        testClient
                .get()
                .uri("/api/v1/estacionamentos")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "ana@email.com.br", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody()
                .jsonPath("status").isEqualTo("403")
                .jsonPath("path").isEqualTo("/api/v1/estacionamentos")
                .jsonPath("method").isEqualTo("GET");
    }
}
