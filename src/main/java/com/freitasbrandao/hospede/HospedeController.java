package com.freitasbrandao.hospede;

import java.net.URI;
import java.util.List;
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
        return hospedeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hospede> criar(@RequestBody Hospede hospede) {
        hospede.setId(null);
        Hospede salvo = hospedeRepository.save(hospede);
        return ResponseEntity
                .created(URI.create("/api/hospedes/" + salvo.getId()))
                .body(salvo);
    }

    @PutMapping
    public ResponseEntity<Hospede> atualizar(@RequestBody Hospede hospede) {
        if (hospede.getId() == null || !hospedeRepository.existsById(hospede.getId())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(hospedeRepository.save(hospede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!hospedeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        hospedeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
