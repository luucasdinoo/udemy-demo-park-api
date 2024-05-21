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

@Entity
@Table(name = "clientes")
@EntityListeners(AuditingEntityListener.class) // Auditoria
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

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
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
