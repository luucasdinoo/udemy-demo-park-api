package com.dev.dino.demoparkapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

// Classe de auditoria
@EnableJpaAuditing
@Configuration
public class SpringJpaAuditingConfig implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Retorna um objeto de autenticação
        if (authentication != null && authentication.isAuthenticated()) // Verificar se o objeto de autenticação é != de null e está autenticado.
            return Optional.of(authentication.getName()); // Retorna o username do usuario autenticado

        return null;
    }
}
