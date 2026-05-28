package com.freitasbrandao.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvolucaoResponseDTO {
    private Long id;
    private Long pessoaId;
    private LocalDate data;
    private String descricao;
    private String responsavel;
}