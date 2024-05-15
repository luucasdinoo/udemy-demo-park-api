package com.dev.dino.demoparkapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class UsuarioCreateDto implements Serializable {

    @NotBlank
    @Email(message = "Formado do e-mail está inválido")
    private String username;
    @NotBlank
    @Size(min = 6, max = 6)
    private String password;
}
