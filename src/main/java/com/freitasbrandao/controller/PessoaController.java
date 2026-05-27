package com.freitasbrandao.controller;

import com.freitasbrandao.dto.PessoaRequestDTO;
import com.freitasbrandao.dto.PessoaResponseDTO;
import com.freitasbrandao.dto.PessoaResumoDTO;
import com.freitasbrandao.model.Genero;
import com.freitasbrandao.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponseDTO> cadastrar(
            @Valid @RequestBody PessoaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pessoaService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaResumoDTO>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String protocolo,
            @RequestParam(required = false) Genero genero,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(
                pessoaService.listar(nome, cpf, protocolo, genero, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PessoaRequestDTO dto) {
        return ResponseEntity.ok(pessoaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}