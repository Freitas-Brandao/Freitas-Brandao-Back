package com.freitasbrandao.repository;

import com.freitasbrandao.model.Evolucao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EvolucaoRepository extends JpaRepository<Evolucao, Long> {

    List<Evolucao> findByPessoaIdOrderByDataDesc(Long pessoaId);

    Optional<Evolucao> findByIdAndPessoaId(Long id, Long pessoaId);

    void deleteByIdAndPessoaId(Long id, Long pessoaId);

    void deleteByPessoaId(Long pessoaId);
}
