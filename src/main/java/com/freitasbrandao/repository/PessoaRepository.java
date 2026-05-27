package com.freitasbrandao.repository;

import com.freitasbrandao.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>,
        JpaSpecificationExecutor<Pessoa> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByProtocolo(String protocolo);
    boolean existsByCpf(String cpf);
    boolean existsByProtocolo(String protocolo);
}