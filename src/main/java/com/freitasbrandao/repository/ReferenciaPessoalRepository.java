package com.freitasbrandao.repository;

import com.freitasbrandao.model.ReferenciaPessoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferenciaPessoalRepository extends JpaRepository<ReferenciaPessoal, Long> {

    List<ReferenciaPessoal> findByPessoaId(Long pessoaId);

    Optional<ReferenciaPessoal> findByIdAndPessoaId(Long id, Long pessoaId);

    void deleteByIdAndPessoaId(Long id, Long pessoaId);

    void deleteByPessoaId(Long pessoaId);
}
