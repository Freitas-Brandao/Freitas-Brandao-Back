package com.freitasbrandao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesligamentoRequestDTO {

    @NotNull(message = "A data do desligamento é obrigatória.")
    private LocalDate data;

    private String motivo;

    @Builder.Default
    private Boolean devolveuRoupas = false;

    @Builder.Default
    private Boolean levouDocumentos = false;

    @Builder.Default
    private Boolean temLesoes = false;

    @Size(max = 255, message = "O técnico responsável deve ter no máximo 255 caracteres.")
    private String tecnicoResponsavel;

    private String observacoes;
}