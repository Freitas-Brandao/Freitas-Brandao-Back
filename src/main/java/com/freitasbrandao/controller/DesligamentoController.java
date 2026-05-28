package com.freitasbrandao.controller;

import com.freitasbrandao.dto.DesligamentoRequestDTO;
import com.freitasbrandao.dto.DesligamentoResponseDTO;
import com.freitasbrandao.service.DesligamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/{pessoaId}/desligamentos")
@RequiredArgsConstructor
public class DesligamentoController {

    private final DesligamentoService desligamentoService;

    @PostMapping
    public ResponseEntity<DesligamentoResponseDTO> criar(
            @PathVariable Long pessoaId,
            @Valid @RequestBody DesligamentoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(desligamentoService.criar(pessoaId, request));
    }

    @GetMapping
    public ResponseEntity<List<DesligamentoResponseDTO>> listar(
            @PathVariable Long pessoaId) {
        return ResponseEntity.ok(desligamentoService.listar(pessoaId));
    }

    @GetMapping("/{desligamentoId}")
    public ResponseEntity<DesligamentoResponseDTO> buscarPorId(
            @PathVariable Long pessoaId,
            @PathVariable Long desligamentoId) {
        return ResponseEntity.ok(
                desligamentoService.buscarPorId(pessoaId, desligamentoId)
        );
    }

    @PutMapping("/{desligamentoId}")
    public ResponseEntity<DesligamentoResponseDTO> atualizar(
            @PathVariable Long pessoaId,
            @PathVariable Long desligamentoId,
            @Valid @RequestBody DesligamentoRequestDTO request) {
        return ResponseEntity.ok(
                desligamentoService.atualizar(pessoaId, desligamentoId, request)
        );
    }

    @DeleteMapping("/{desligamentoId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long pessoaId,
            @PathVariable Long desligamentoId) {
        desligamentoService.deletar(pessoaId, desligamentoId);
        return ResponseEntity.noContent().build();
    }
}