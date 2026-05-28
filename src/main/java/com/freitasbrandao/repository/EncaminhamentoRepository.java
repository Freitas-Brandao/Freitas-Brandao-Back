package com.freitasbrandao.repository;

import com.freitasbrandao.model.Encaminhamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EncaminhamentoRepository extends JpaRepository<Encaminhamento, Long> {

    List<Encaminhamento> findByPessoaIdOrderByDataDesc(Long pessoaId);

    Optional<Encaminhamento> findByIdAndPessoaId(Long id, Long pessoaId);

    void deleteByIdAndPessoaId(Long id, Long pessoaId);
}