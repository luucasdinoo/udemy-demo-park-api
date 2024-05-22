package com.dev.dino.demoparkapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "clientes_tem_vagas")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ClienteVaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_recibo", nullable = false, unique = true, length = 15)
    private String recibo;
    @Column(name = "placa", nullable = false, length = 8)
    private String placa;
    @Column(name = "marca", nullable = false, length = 45)
    private String marca;
    @Column(name = "modelo", nullable = false, length = 45)
    private String modelo;
    @Column(name = "cor", nullable = false, length = 45)
    private String cor;
    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;
    @Column(name = "data_saida")
    private LocalDateTime datSaida;
    @Column(name = "valor", columnDefinition = "decimal(7,2)")
    private BigDecimal valor;
    @Column(name = "desconto", columnDefinition = "decimal(7,2)")
    private BigDecimal desconto;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_vaga", nullable = false)
    private Vaga vaga;

    @CreatedDate // Auditoria
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @LastModifiedDate // Auditoria
    @Column(name = "data_atualização")
    private LocalDateTime dataAtualizacao;
    @CreatedBy // Auditoria
    @Column(name = "criado_por")
    private String criadoPor;
    @Column(name = "atualizado_por")
    @LastModifiedBy // Auditoria
    private String atualizadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteVaga that = (ClienteVaga) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
