package com.dev.dino.demoparkapi.dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class UsuarioResponseDto {

    private Long id;
    private String username;
    private String role;
}
