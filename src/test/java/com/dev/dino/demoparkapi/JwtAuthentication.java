package com.dev.dino.demoparkapi;

import com.dev.dino.demoparkapi.dto.UsuarioLoginDto;
import com.dev.dino.demoparkapi.jwt.JwtToken;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

public class JwtAuthentication {

    // Método de autenticação para ser adicionada aos testes, o método retorna o token de autenticação
    public static Consumer<HttpHeaders> getHearderAuthorization(WebTestClient client, String username, String password){
        String token = client
                .post()
                .uri("/api/v1/auth")
                .bodyValue(new UsuarioLoginDto(username,password))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody().getToken();
        return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}
