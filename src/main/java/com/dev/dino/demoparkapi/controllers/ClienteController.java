package com.dev.dino.demoparkapi.controllers;

import com.dev.dino.demoparkapi.dto.ClienteCreateDto;
import com.dev.dino.demoparkapi.dto.ClienteResponseDto;
import com.dev.dino.demoparkapi.dto.mapper.ClienteMapper;
import com.dev.dino.demoparkapi.entity.Cliente;
import com.dev.dino.demoparkapi.jwt.JwtUserDetails;
import com.dev.dino.demoparkapi.services.ClienteService;
import com.dev.dino.demoparkapi.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto,
                                                     @AuthenticationPrincipal JwtUserDetails userDetails){
        Cliente cliente = ClienteMapper.toCliente(dto);
        cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId())); // Especifica o usuario ao qual o cliente pertence, atraves do objeto JwtUserDetails
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
    }
}
