package com.freitasbrandao.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficioSocialRequestDTO {

    @Builder.Default
    private Boolean bolsaFamilia = false;

    @Builder.Default
    private Boolean bpc = false;

    @Builder.Default
    private Boolean auxilioBrasil = false;

    @Builder.Default
    private Boolean seguroDesemprego = false;

    @Size(max = 5000, message = "O campo outros Beneficios deve ter no máximo 5000 caracteres.")
    private String outrosBeneficios;
}