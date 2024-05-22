package com.dev.dino.demoparkapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "vagas")
@EntityListeners(AuditingEntityListener.class)
public class Vaga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo", nullable = false, unique = true, length = 4)
    private String codigo;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusVaga status;

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


    public enum StatusVaga{
        LIVRE, OCUPADA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return Objects.equals(id, vaga.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
