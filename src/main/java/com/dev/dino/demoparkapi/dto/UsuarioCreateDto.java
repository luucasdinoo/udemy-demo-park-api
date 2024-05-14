package com.dev.dino.demoparkapi.dto;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class UsuarioCreateDto implements Serializable {

    private String username;
    private String password;
}
