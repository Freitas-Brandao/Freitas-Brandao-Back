package com.freitasbrandao.repository;

import com.freitasbrandao.model.Genero;
import com.freitasbrandao.model.Pessoa;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PessoaSpecification {

    public static Specification<Pessoa> filtrar(String nome, String cpf,
                                                String protocolo, Genero genero) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null && !nome.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("nome")),
                        "%" + nome.toLowerCase() + "%"
                ));
            }
            if (cpf != null && !cpf.isBlank()) {
                predicates.add(cb.like(
                        root.get("cpf"),
                        "%" + cpf.replaceAll("[^\\d]", "") + "%"
                ));
            }
            if (protocolo != null && !protocolo.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("protocolo")),
                        "%" + protocolo.toLowerCase() + "%"
                ));
            }
            if (genero != null) {
                predicates.add(cb.equal(root.get("genero"), genero));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}