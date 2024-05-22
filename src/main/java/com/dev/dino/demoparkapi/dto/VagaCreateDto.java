package com.dev.dino.demoparkapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class VagaCreateDto {

    @NotBlank
    @Size(min = 4, max = 4)
    private String codigo;
    @NotBlank
    @Pattern(regexp = "LIVRE|OCUPADA")
    private String status;
}
