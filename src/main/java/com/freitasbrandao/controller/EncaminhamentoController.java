package com.freitasbrandao.controller;

import com.freitasbrandao.dto.EncaminhamentoRequestDTO;
import com.freitasbrandao.dto.EncaminhamentoResponseDTO;
import com.freitasbrandao.service.EncaminhamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/{pessoaId}/encaminhamentos")
@RequiredArgsConstructor
public class EncaminhamentoController {

    private final EncaminhamentoService encaminhamentoService;

    @PostMapping
    public ResponseEntity<EncaminhamentoResponseDTO> criar(
            @PathVariable Long pessoaId,
            @Valid @RequestBody EncaminhamentoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(encaminhamentoService.criar(pessoaId, request));
    }

    @GetMapping
    public ResponseEntity<List<EncaminhamentoResponseDTO>> listar(
            @PathVariable Long pessoaId) {
        return ResponseEntity.ok(encaminhamentoService.listar(pessoaId));
    }

    @GetMapping("/{encaminhamentoId}")
    public ResponseEntity<EncaminhamentoResponseDTO> buscarPorId(
            @PathVariable Long pessoaId,
            @PathVariable Long encaminhamentoId) {
        return ResponseEntity.ok(
                encaminhamentoService.buscarPorId(pessoaId, encaminhamentoId)
        );
    }

    @PutMapping("/{encaminhamentoId}")
    public ResponseEntity<EncaminhamentoResponseDTO> atualizar(
            @PathVariable Long pessoaId,
            @PathVariable Long encaminhamentoId,
            @Valid @RequestBody EncaminhamentoRequestDTO request) {
        return ResponseEntity.ok(
                encaminhamentoService.atualizar(pessoaId, encaminhamentoId, request)
        );
    }

    @DeleteMapping("/{encaminhamentoId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long pessoaId,
            @PathVariable Long encaminhamentoId) {
        encaminhamentoService.deletar(pessoaId, encaminhamentoId);
        return ResponseEntity.noContent().build();
    }
}