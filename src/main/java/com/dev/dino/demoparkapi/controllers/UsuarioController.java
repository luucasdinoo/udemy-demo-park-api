package com.dev.dino.demoparkapi.controllers;

import com.dev.dino.demoparkapi.entity.Usuario;
import com.dev.dino.demoparkapi.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario obj) {
        Usuario user = usuarioService.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
