package com.mballem.demo_park_api;

import com.mballem.demo_park_api.web.dto.UsuarioCreateDto;
import com.mballem.demo_park_api.web.dto.UsuarioResponseDto;
import com.mballem.demo_park_api.web.dto.UsuarioSenhaDto;
import com.mballem.demo_park_api.web.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioIT {

    @Autowired
    WebTestClient testClient;

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void createUsuario_ComUsernameEPasswordValidos_RetornarUsuarioCriadoComStatus201() {
        // faz a configuração do corpo da requisição
        UsuarioResponseDto responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@gmail.com", "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("tody@gmail.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("USER");
    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void createUsuario_ComUsernameInvalido_RetornarErrorMessageStatus422() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@gmail.", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void createUsuario_ComPasswordInvalido_RetornarErrorMessageStatus422() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@gmail.com", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@gmail.com", "12345"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@gmail.com", "1234567"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@gmail.com", "      "))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void createUsuario_ComUsernameRepetido_RetornarErrorMessageComStatus409() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("antonio@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void getById_UsuarioExistente_RetornarUsuarioEncontradoComStatus200() {
        // faz a configuração do corpo da requisição
        UsuarioResponseDto responseBody = testClient
                .get()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(100);
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("antonio@gmail.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");

        responseBody = testClient
                .get()
                .uri("/api/usuarios/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(101);
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("giuli@gmail.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("USER");

        responseBody = testClient
                .get()
                .uri("/api/usuarios/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "giuli@gmail.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(101);
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("giuli@gmail.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("USER");

        responseBody = testClient
                .get()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "giuli@gmail.com", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();

    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void getById_UsuarioInexistente_RetornarErrorMessageComStatus404() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/usuarios/200")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);


    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void getById_ComUsuarioClienteBuscandoOutroCliente_RetornarErrorMessageComStatus403() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/usuarios/102")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "giuli@gmail.com", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);

    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void updatePassword_ComDadosValidos_RetornarSenhaAlteradaComSucessoComStatus204() {
        // faz a configuração do corpo da requisição
        testClient
                .patch()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "123456", "123456"))
                .exchange()
                .expectStatus().isNoContent();

        // como esse metodo nao possui um retorno (noContent) não precisa testar o objeto

        testClient
                .patch()
                .uri("/api/usuarios/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "giuli@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "123456", "123456"))
                .exchange()
                .expectStatus().isNoContent();

        // como esse metodo nao possui um retorno (noContent) não precisa testar o objeto
    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void updatePassword_ComUsuariosDiferentes_RetornarErrorMessageComStatus403() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .patch()
                .uri("/api/usuarios/101")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "123456", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);

        responseBody = testClient
                .patch()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "giuli@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "123456", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void updatePassword_ComCamposInvalidos_RetornarErrorMessageComStatus422() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .patch()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("", "", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .patch()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("1234567", "1234567", "1234567"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        responseBody = testClient
                .patch()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("12345", "12345", "12345"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
    }

    @Test
    // nome da funcao = metodoQueVaiSerTestado_OqueVaiSerVerificado_OqueSeEsperaDeResposta
    public void updatePassword_ComSenhasInvalidas_RetornarErrorMessageComStatus400() {
        // faz a configuração do corpo da requisição
        ErrorMessage responseBody = testClient
                .patch()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("654321", "123456", "123456"))
                .exchange()
                .expectStatus().isEqualTo(400)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);

        responseBody = testClient
                .patch()
                .uri("/api/usuarios/100")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "000000", "123456"))
                .exchange()
                .expectStatus().isEqualTo(400)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);


    }

    @Test
    public void getAll_ObtemTodosOsUsuarios_RetornarTodosOsUsuariosComStatus200() {
        // faz a configuração do corpo da requisição
        List<UsuarioResponseDto> responseBody = testClient
                .get()
                .uri("/api/usuarios")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "antonio@gmail.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(3);
    }

    @Test
    public void getAll_UsuarioClienteObtemTodosOsUsuarios_RetornarErrorMessageComStatus403() {
        // faz a configuração do corpo da requisição
        List<UsuarioResponseDto> responseBody = testClient
                .get()
                .uri("/api/usuarios")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "giuli@gmail.com", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBodyList(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        // realiza os testes
        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();

    }
}
