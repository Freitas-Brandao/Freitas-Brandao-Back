package com.freitasbrandao.service;

import com.freitasbrandao.dto.AlterarSenhaRequestDTO;
import com.freitasbrandao.dto.UsuarioResponseDTO;
import com.freitasbrandao.exception.ResourceNotFoundException;
import com.freitasbrandao.model.Usuario;
import com.freitasbrandao.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UsuarioResponseDTO usuarioLogado(Authentication authentication) {
        Usuario usuario = usuarioRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário autenticado não encontrado."));

        return toResponse(usuario);
    }

    @Transactional
    public void alterarSenha(Authentication authentication, AlterarSenhaRequestDTO request) {
        Usuario usuario = usuarioRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário autenticado não encontrado."));

        if (!passwordEncoder.matches(request.getSenhaAtual(), usuario.getSenhaHash())) {
            throw new IllegalArgumentException("A senha atual está incorreta.");
        }

        usuario.setSenhaHash(passwordEncoder.encode(request.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

    private UsuarioResponseDTO toResponse(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .dataCriacao(usuario.getDataCriacao())
                .build();
    }
}