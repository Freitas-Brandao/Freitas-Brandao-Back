package com.freitasbrandao.controller;

import com.freitasbrandao.dto.AlterarSenhaRequestDTO;
import com.freitasbrandao.dto.UsuarioResponseDTO;
import com.freitasbrandao.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> me(Authentication authentication) {
        return ResponseEntity.ok(usuarioService.usuarioLogado(authentication));
    }

    @PutMapping("/alterar-senha")
    public ResponseEntity<Void> alterarSenha(
            Authentication authentication,
            @Valid @RequestBody AlterarSenhaRequestDTO request) {
        usuarioService.alterarSenha(authentication, request);
        return ResponseEntity.noContent().build();
    }
}