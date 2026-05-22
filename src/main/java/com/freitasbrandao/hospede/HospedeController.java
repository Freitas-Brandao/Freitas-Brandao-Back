package com.freitasbrandao.hospede;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {

    private final HospedeRepository hospedeRepository;

    public HospedeController(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    @GetMapping
    public List<Hospede> listar() {
        return hospedeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospede> buscarPorId(@PathVariable Long id) {
        Long hospedeId = Objects.requireNonNull(id, "id e obrigatorio");
        return hospedeRepository.findById(hospedeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hospede> criar(@RequestBody Hospede hospede) {
        hospede.setId(null);
        Hospede salvo = hospedeRepository.save(hospede);
        Long idGerado = Objects.requireNonNull(salvo.getId(), "id gerado e obrigatorio");
        URI localizacao = Objects.requireNonNull(
                URI.create("/api/hospedes/" + idGerado),
                "uri de localizacao e obrigatoria"
        );
        return ResponseEntity
                .created(localizacao)
                .body(salvo);
    }

    @PutMapping
    public ResponseEntity<Hospede> atualizar(@RequestBody Hospede hospede) {
        Long hospedeId = hospede.getId();
        if (hospedeId == null || !hospedeRepository.existsById(hospedeId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hospedeRepository.save(hospede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        Long hospedeId = Objects.requireNonNull(id, "id e obrigatorio");
        if (!hospedeRepository.existsById(hospedeId)) {
            return ResponseEntity.notFound().build();
        }

        hospedeRepository.deleteById(hospedeId);
        return ResponseEntity.noContent().build();
    }
}
