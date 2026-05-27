package com.freitasbrandao.controller;

import com.freitasbrandao.dto.BeneficioSocialRequestDTO;
import com.freitasbrandao.dto.BeneficioSocialResponseDTO;
import com.freitasbrandao.service.BeneficioSocialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas/{pessoaId}/beneficios")
@RequiredArgsConstructor
public class BeneficioSocialController {

    private final BeneficioSocialService beneficioSocialService;

    @PostMapping
    public ResponseEntity<BeneficioSocialResponseDTO> criar(
            @PathVariable Long pessoaId,
            @Valid @RequestBody BeneficioSocialRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(beneficioSocialService.criar(pessoaId, request));
    }

    @GetMapping
    public ResponseEntity<BeneficioSocialResponseDTO> buscar(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(beneficioSocialService.buscarPorPessoaId(pessoaId));
    }

    @PutMapping
    public ResponseEntity<BeneficioSocialResponseDTO> atualizar(
            @PathVariable Long pessoaId,
            @Valid @RequestBody BeneficioSocialRequestDTO request) {
        return ResponseEntity.ok(beneficioSocialService.atualizar(pessoaId, request));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long pessoaId) {
        beneficioSocialService.deletar(pessoaId);
        return ResponseEntity.noContent().build();
    }
}