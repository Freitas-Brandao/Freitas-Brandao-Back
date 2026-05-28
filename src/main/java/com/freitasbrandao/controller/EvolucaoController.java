package com.freitasbrandao.controller;

import com.freitasbrandao.dto.EvolucaoRequestDTO;
import com.freitasbrandao.dto.EvolucaoResponseDTO;
import com.freitasbrandao.service.EvolucaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/{pessoaId}/evolucoes")
@RequiredArgsConstructor
public class EvolucaoController {

    private final EvolucaoService evolucaoService;

    @PostMapping
    public ResponseEntity<EvolucaoResponseDTO> criar(
            @PathVariable Long pessoaId,
            @Valid @RequestBody EvolucaoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(evolucaoService.criar(pessoaId, request));
    }

    @GetMapping
    public ResponseEntity<List<EvolucaoResponseDTO>> listar(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(evolucaoService.listar(pessoaId));
    }

    @GetMapping("/{evolucaoId}")
    public ResponseEntity<EvolucaoResponseDTO> buscarPorId(
            @PathVariable Long pessoaId,
            @PathVariable Long evolucaoId) {
        return ResponseEntity.ok(evolucaoService.buscarPorId(pessoaId, evolucaoId));
    }

    @PutMapping("/{evolucaoId}")
    public ResponseEntity<EvolucaoResponseDTO> atualizar(
            @PathVariable Long pessoaId,
            @PathVariable Long evolucaoId,
            @Valid @RequestBody EvolucaoRequestDTO request) {
        return ResponseEntity.ok(evolucaoService.atualizar(pessoaId, evolucaoId, request));
    }

    @DeleteMapping("/{evolucaoId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long pessoaId,
            @PathVariable Long evolucaoId) {
        evolucaoService.deletar(pessoaId, evolucaoId);
        return ResponseEntity.noContent().build();
    }
}