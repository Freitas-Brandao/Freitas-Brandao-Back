package com.freitasbrandao.repository;

import com.freitasbrandao.model.BeneficioSocial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficioSocialRepository extends JpaRepository<BeneficioSocial, Long> {
    Optional<BeneficioSocial> findByPessoaId(Long pessoaId);
    boolean existsByPessoaId(Long pessoaId);
    void deleteByPessoaId(Long pessoaId);
}