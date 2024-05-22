package com.dev.dino.demoparkapi.services;

import com.dev.dino.demoparkapi.entity.ClienteVaga;
import com.dev.dino.demoparkapi.repositories.ClienteVagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteVagaService {

    private final ClienteVagaRepository repository;

    @Transactional
    public ClienteVaga salvar(ClienteVaga clienteVaga){
        return repository.save(clienteVaga);
    }
}
