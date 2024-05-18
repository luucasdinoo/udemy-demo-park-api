package com.dev.dino.demoparkapi.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Objeto de resposta (token)
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class JwtToken {

    private String token;
}
