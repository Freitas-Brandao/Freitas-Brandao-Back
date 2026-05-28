package com.freitasbrandao.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncaminhamentoResponseDTO {
    private Long id;
    private Long pessoaId;
    private LocalDate data;
    private String destino;
    private String descricao;
}