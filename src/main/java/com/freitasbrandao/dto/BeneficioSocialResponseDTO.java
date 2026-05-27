package com.freitasbrandao.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficioSocialResponseDTO {
    private Long id;
    private Long pessoaId;
    private Boolean bolsaFamilia;
    private Boolean bpc;
    private Boolean auxilioBrasil;
    private Boolean seguroDesemprego;
    private String outrosBeneficios;
}