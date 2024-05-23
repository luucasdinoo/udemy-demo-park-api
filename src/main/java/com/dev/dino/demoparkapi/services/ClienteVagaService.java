package com.dev.dino.demoparkapi.services;

import com.dev.dino.demoparkapi.entity.ClienteVaga;
import com.dev.dino.demoparkapi.entity.exception.EntityNotFoundExceptionSearch;
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

    @Transactional(readOnly = true)
    public ClienteVaga buscarPorRecibo(String recibo) {
        return repository.findByReciboAndDatSaidaIsNull(recibo).orElseThrow(
                ()-> new EntityNotFoundExceptionSearch(String.format("Recibo '%s' não encontrado no sistema ou check-out já realizado", recibo)));
    }

    @Transactional(readOnly = true)
    public long getTotalDeVezesEstacionamentoCompleto(String cpf) {
        return repository.countByClienteCpfAndDatSaidaIsNotNull(cpf);
    }
}
