package com.freitasbrandao.dto;

import com.freitasbrandao.model.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

// Usado na listagem — SEM o campo conteudo (BYTEA)
@Data
@AllArgsConstructor
public class DocumentoResumoDTO {
    private Long id;
    private TipoDocumento tipo;
    private String nomeOriginal;
    private String contentType;
    private LocalDateTime dataUpload;
}