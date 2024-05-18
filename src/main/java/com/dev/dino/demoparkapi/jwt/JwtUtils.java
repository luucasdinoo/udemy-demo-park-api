package com.dev.dino.demoparkapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

// Classe para gerar o token JWT
@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer "; // Prefixo do token
    public static final String JWT_AUTHORIZATION = "Authorization"; // Cabeçalho
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789"; // Chave secreta com 32 caracteres+
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 30;

    private JwtUtils(){}

    // Método privado e estático para :Gerar uma chave secreta (SecretKey) usada para criptografia ou geração de tokens
    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Método privado e estático para :Calculo para expiração do token
    private static Date toExpiredDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); // Transformar o Date recebido em um LocalDateTime
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES); // Acrescentar valores de tempo
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant()); // Retorna o periodo de tempo até a expiração do token
    }

    // Método públic e estático para: Criação do token de autenticação
    public static JwtToken createToken(String username, String role){
        Date issuedAt = new Date();
        Date limit = toExpiredDate(issuedAt);

        String token = Jwts.builder()
                .header().add("typ","JWT")// Token do tipo JWT
                .and()
                .subject(username) // Nome do usuário ou id
                .issuedAt(issuedAt) // Data de geração do token
                .expiration(limit) // Data de expiração do token
                .signWith(getSecretKey()) // Chave gerada no método getSecretKey()
                .claim("role",role) // Perfil do usuário
                .compact(); // FIM

        return new JwtToken(token);
    }

    // Método privado e estático para: Recuperar o conteudo do token
    private static Claims getClaimsFromToken(String token) {
        try{
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(refactorToken(token))
                    .getPayload();
        }
        catch (JwtException e){
            log.error(String.format("Token invalido: %s", e.getMessage()));
        }
        return null;
    }

    // Método privado e estático para: Verificar se o token tem o prefixo BEARER e retirar
    private static String refactorToken(String token) {
        if (token.contains(JWT_BEARER))
            return token.substring(JWT_BEARER.length());

        return token;
    }

    // Método públic e estático para: Recuperar o username dentro do token
    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Método públic e estático para: Testar a validade do token
    public static boolean isTokenValid(String token) {
        try{
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(refactorToken(token));
            return true;
        }
        catch (JwtException e){
            log.error(String.format("Token invalid: %s", e.getMessage()));
        }
        return false;
    }

}
