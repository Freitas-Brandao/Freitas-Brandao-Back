package com.freitasbrandao.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesligamentoResponseDTO {
    private Long id;
    private Long pessoaId;
    private LocalDate data;
    private String motivo;
    private Boolean devolveuRoupas;
    private Boolean levouDocumentos;
    private Boolean temLesoes;
    private String tecnicoResponsavel;
    private String observacoes;
}