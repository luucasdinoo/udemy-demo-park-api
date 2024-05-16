package com.dev.dino.demoparkapi.services;

import com.dev.dino.demoparkapi.entity.Usuario;
import com.dev.dino.demoparkapi.entity.exception.EntityNotFoundExceptionSearch;
import com.dev.dino.demoparkapi.entity.exception.PasswordInvalidException;
import com.dev.dino.demoparkapi.entity.exception.UserNameUniqueViolationException;
import com.dev.dino.demoparkapi.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario create(Usuario obj) {
        try {
            obj.setPassword(passwordEncoder.encode(obj.getPassword()));
            return usuarioRepository.save(obj);
        }
        catch (DataIntegrityViolationException ex){
            throw new UserNameUniqueViolationException(String.format("Username {%s} já cadastrado", obj.getUsername()));
        }
    }
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundExceptionSearch(String.format("Usuario id=%s não encontrado", id)));
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha))
            throw new PasswordInvalidException("Nova senha não confere com corfirmação de senha");

        Usuario user = buscarPorId(id);
        if (!passwordEncoder.matches(senhaAtual, user.getPassword()))
            throw new PasswordInvalidException("Sua senha não confere");

        user.setPassword(passwordEncoder.encode(novaSenha));
        return user;
    }
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundExceptionSearch(String.format("Usuario com username=%s não encontrado", username)));
    }

    @Transactional(readOnly = true)

    public Usuario.Role buscarRolePorUsername(String username) {
        return usuarioRepository.findRoleByUsername(username);
    }
}
