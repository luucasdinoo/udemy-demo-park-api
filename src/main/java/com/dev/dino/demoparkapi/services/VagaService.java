package com.dev.dino.demoparkapi.services;

import com.dev.dino.demoparkapi.entity.Vaga;
import com.dev.dino.demoparkapi.entity.exception.CodigoUniqueViolationException;
import com.dev.dino.demoparkapi.entity.exception.EntityNotFoundExceptionSearch;
import com.dev.dino.demoparkapi.repositories.VagaRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepositoy vagaRepositoy;

    @Transactional
    public Vaga salvar(Vaga vaga){
        try {
            return vagaRepositoy.save(vaga);
        }
        catch (DataIntegrityViolationException ex){
            throw new CodigoUniqueViolationException(String.format("Vaga com codigo '%s' já cadastrada", vaga.getCodigo()));
        }
    }

    @Transactional(readOnly = true)
    public Vaga buscarPorCodigo(String codigo){
        return vagaRepositoy.findByCodigo(codigo).orElseThrow(() -> new EntityNotFoundExceptionSearch(String.format("Vaga com código '%s' não foi encontrada", codigo)));
    }
}
