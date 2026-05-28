package com.freitasbrandao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncaminhamentoRequestDTO {

    @NotNull(message = "A data do encaminhamento é obrigatória.")
    private LocalDate data;

    @NotBlank(message = "O destino do encaminhamento é obrigatório.")
    @Size(max = 255, message = "O destino deve ter no máximo 255 caracteres.")
    private String destino;

    private String descricao;
}