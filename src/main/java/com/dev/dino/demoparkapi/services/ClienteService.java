package com.dev.dino.demoparkapi.services;

import com.dev.dino.demoparkapi.entity.Cliente;
import com.dev.dino.demoparkapi.entity.exception.CpfUniqueViolationException;
import com.dev.dino.demoparkapi.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        }
        catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException(String.format("CPF '%s' não pode ser cadastrado, já existe no cinema", cliente.getCpf()));
        }
    }
}
