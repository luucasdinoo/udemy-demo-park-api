package com.dev.dino.demoparkapi.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Classe Filter para interceptar as requisições da API e testar se o token é válido ou não para acessar a requisção
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtUserDetailsService detailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION); // Recuperar o token a partir do cabeçalho AUTHORIZATION que irá chegar nas requisições

        if (token == null || !token.startsWith(JwtUtils.JWT_BEARER)) { // Verificar se o token é nulo ou não inicia com o prefixo BEARER
            log.info("JWT token está nulo, vazio ou não iniciado com 'Bearer");
            filterChain.doFilter(request,response); // Retornar request e response
            return; // Forçar saida do filtro
        }

        if (!JwtUtils.isTokenValid(token)){ // Verificar se o token é válido ou não
            log.warn("JWT token está inválido ou expirado"); // Retornar request e response
            filterChain.doFilter(request,response);
            return; // Forçar saida do filtro
        }

        // Apoós passar pelo processo de validação do token > Processo de autenticação
        String username = JwtUtils.getUsernameFromToken(token); // Recupera o username dentro do token
        toAuthentication(request, username);
        filterChain.doFilter(request,response);
    }

    // Método privado para:
    private void toAuthentication(HttpServletRequest request, String username) {
        UserDetails userDetails = detailsService.loadUserByUsername(username);

        // Testar a senha e ir para o proximo passo
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Passando o objeto de requisição (request) para a parte de autenticação do SpringSecurity
        SecurityContextHolder.getContext().setAuthentication(authenticationToken); // FIM
    }
}
