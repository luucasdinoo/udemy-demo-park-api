package com.dev.dino.demoparkapi.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor

public class EstacionamentoResponseDto {

    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    public String clienteCpf;
    private String recibo;
    private LocalDateTime dataEntrada;
    private String vagaCodigo;
    private BigDecimal valor;
    private BigDecimal desconto;
}
