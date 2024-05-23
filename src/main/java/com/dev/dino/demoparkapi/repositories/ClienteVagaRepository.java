package com.dev.dino.demoparkapi.repositories;

import com.dev.dino.demoparkapi.entity.ClienteVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteVagaRepository extends JpaRepository<ClienteVaga, Long> {

    Optional<ClienteVaga> findByReciboAndDatSaidaIsNull(String recibo);
}
