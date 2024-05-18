package com.dev.dino.demoparkapi.jwt;

import com.dev.dino.demoparkapi.entity.Usuario;
import com.dev.dino.demoparkapi.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Classe para o service de UserDetails
@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    // Método público para: Fazer uma consulta por username/id > retornar um novo objeto UserDatails > Spring conferir se a senha confere com a senha armazenada no BD
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.buscarPorUsername(username);
        return new JwtUserDetails(usuario);
    }

    // Método públic para: Gerar o token JWT
    public JwtToken getTokenAuthenticated(String username){
        Usuario.Role role = usuarioService.buscarRolePorUsername(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
