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
public class EvolucaoRequestDTO {

    @NotNull(message = "A data da evolução é obrigatória.")
    private LocalDate data;

    @NotBlank(message = "A descrição da evolução é obrigatória.")
    private String descricao;

    @Size(max = 255, message = "O responsável deve ter no máximo 255 caracteres.")
    private String responsavel;
}