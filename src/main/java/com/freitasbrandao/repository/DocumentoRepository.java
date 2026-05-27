package com.freitasbrandao.repository;

import com.freitasbrandao.model.Documento;
import com.freitasbrandao.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    // Busca metadados sem carregar o BYTEA — nunca usar findAll com conteudo
    @Query("""
        SELECT new com.freitasbrandao.dto.DocumentoResumoDTO(
            d.id, d.tipo, d.nomeOriginal, d.contentType, d.dataUpload
        )
        FROM Documento d
        WHERE d.pessoa.id = :pessoaId
    """)
    List<com.freitasbrandao.dto.DocumentoResumoDTO> findMetadadosByPessoaId(
            @Param("pessoaId") Long pessoaId);

    Optional<Documento> findByIdAndPessoaId(Long id, Long pessoaId);

    Optional<Documento> findFirstByPessoaIdAndTipo(Long pessoaId, TipoDocumento tipo);

    void deleteByIdAndPessoaId(Long id, Long pessoaId);
}