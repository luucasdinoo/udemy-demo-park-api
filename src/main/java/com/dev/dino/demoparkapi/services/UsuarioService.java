package com.dev.dino.demoparkapi.services;

import com.dev.dino.demoparkapi.entity.Usuario;
import com.dev.dino.demoparkapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario create(Usuario obj) {
        return usuarioRepository.save(obj);
    }
}
