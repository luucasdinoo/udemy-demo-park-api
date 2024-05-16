package com.dev.dino.demoparkapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class UsuarioLoginDto {

    @NotBlank
    @Email
    private String username;
    @NotBlank
    @Size(min = 6, max = 6)
    private String password;
}
