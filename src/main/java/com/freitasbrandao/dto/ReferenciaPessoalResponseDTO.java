package com.freitasbrandao.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferenciaPessoalResponseDTO {
    private Long id;
    private Long pessoaId;
    private String nome;
    private String telefone;
    private String parentesco;
}