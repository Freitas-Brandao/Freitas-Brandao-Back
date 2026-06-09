package com.freitasbrandao.repository;

import com.freitasbrandao.model.Desligamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DesligamentoRepository extends JpaRepository<Desligamento, Long> {

    List<Desligamento> findByPessoaIdOrderByDataDesc(Long pessoaId);

    Optional<Desligamento> findByIdAndPessoaId(Long id, Long pessoaId);

    void deleteByIdAndPessoaId(Long id, Long pessoaId);

    void deleteByPessoaId(Long pessoaId);
}
