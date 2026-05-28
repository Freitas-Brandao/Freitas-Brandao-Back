package com.freitasbrandao.service;

import com.freitasbrandao.model.Usuario;
import com.freitasbrandao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getSenhaHash(),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
        );
    }
}