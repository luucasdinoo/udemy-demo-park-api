package com.dev.dino.demoparkapi.controllers;

import com.dev.dino.demoparkapi.dto.UsuarioLoginDto;
import com.dev.dino.demoparkapi.dto.UsuarioResponseDto;
import com.dev.dino.demoparkapi.exception.ErrorMessage;
import com.dev.dino.demoparkapi.jwt.JwtToken;
import com.dev.dino.demoparkapi.jwt.JwtUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Classe Controller para o processo de autenticação
@Tag(name = "Autenticação", description = "Recurso para proceder com a autenticação na API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AutenticacaoController {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Autenticar na API", description = "Recurso de autenticação na API", responses = {
            @ApiResponse(responseCode = "200",description = "Autenticação realizada com suceso e retorno de um Bearer token",content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "400",description = "Credenciais inválidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422",description = "Campos inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto dto, HttpServletRequest request){
        log.info("Processo de autenticacao pelo login {}", dto.getUsername());
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()); // Adicionar username e password para autenticação
            authenticationManager.authenticate(usernamePasswordAuthenticationToken); // Autenticar
            JwtToken token = jwtUserDetailsService.getTokenAuthenticated(dto.getUsername());
            return ResponseEntity.ok(token);
        }
        catch (AuthenticationException e){
            log.warn("Bad Credentials: {}", dto.getUsername());
        }
        return ResponseEntity.badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,"Credenciais inválidas"));
    }
}
