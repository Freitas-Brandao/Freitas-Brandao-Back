package com.freitasbrandao.controller;

import com.freitasbrandao.dto.ReferenciaPessoalRequestDTO;
import com.freitasbrandao.dto.ReferenciaPessoalResponseDTO;
import com.freitasbrandao.service.ReferenciaPessoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/{pessoaId}/referencias")
@RequiredArgsConstructor
public class ReferenciaController {

    private final ReferenciaPessoalService referenciaPessoalService;

    @PostMapping
    public ResponseEntity<ReferenciaPessoalResponseDTO> criar(
            @PathVariable Long pessoaId,
            @Valid @RequestBody ReferenciaPessoalRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(referenciaPessoalService.criar(pessoaId, request));
    }

    @GetMapping
    public ResponseEntity<List<ReferenciaPessoalResponseDTO>> listar(
            @PathVariable Long pessoaId) {
        return ResponseEntity.ok(referenciaPessoalService.listar(pessoaId));
    }

    @GetMapping("/{referenciaId}")
    public ResponseEntity<ReferenciaPessoalResponseDTO> buscarPorId(
            @PathVariable Long pessoaId,
            @PathVariable Long referenciaId) {
        return ResponseEntity.ok(
                referenciaPessoalService.buscarPorId(pessoaId, referenciaId)
        );
    }

    @PutMapping("/{referenciaId}")
    public ResponseEntity<ReferenciaPessoalResponseDTO> atualizar(
            @PathVariable Long pessoaId,
            @PathVariable Long referenciaId,
            @Valid @RequestBody ReferenciaPessoalRequestDTO request) {
        return ResponseEntity.ok(
                referenciaPessoalService.atualizar(pessoaId, referenciaId, request)
        );
    }

    @DeleteMapping("/{referenciaId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long pessoaId,
            @PathVariable Long referenciaId) {
        referenciaPessoalService.deletar(pessoaId, referenciaId);
        return ResponseEntity.noContent().build();
    }
}