package com.dev.dino.demoparkapi.repositories;

import com.dev.dino.demoparkapi.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepositoy extends JpaRepository<Vaga, Long> {
}
