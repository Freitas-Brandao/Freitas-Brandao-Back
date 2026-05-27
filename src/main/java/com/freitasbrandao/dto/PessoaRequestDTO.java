package com.freitasbrandao.dto;

import com.freitasbrandao.model.Genero;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PessoaRequestDTO {

    // Acolhimento
    @NotBlank(message = "Protocolo é obrigatório")
    private String protocolo;

    @NotNull(message = "Data de acolhimento é obrigatória")
    private LocalDate dataAcolhimento;

    private LocalTime horaAcolhimento;

    private Boolean demandaEspontanea = false;

    // Dados pessoais
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String nomeSocial;

    private LocalDate dataNascimento;

    private String naturalidade;

    @NotNull(message = "Gênero é obrigatório")
    private Genero genero;

    @Pattern(regexp = "^\\(\\d{2}\\)\\s?\\d{4,5}-?\\d{4}$",
            message = "Telefone inválido")
    private String telefone;

    // Documentação
    @Pattern(regexp = "^\\d{11}$", message = "CPF deve conter 11 dígitos numéricos")
    private String cpf;

    private String rg;
    private String orgaoExpedidorRg;
    private String tituloEleitoral;
    private String carteiraTrabalho;
    private String certidaoNascimento;

    // Saúde
    private String condicoesSaude;
    private String medicamentosEmUso;
    private String alergiasRestricoes;
    private Boolean usaSubstanciasPsicoativas = false;
    private String substanciasQuais;

    // Atividades
    private String atividadesRealizadas;
    private String oficinasParticipadas;

    // Outros
    private String observacoes;

    // Termo
    private Boolean aceitouTermo = false;
    private LocalDate dataAssinaturaTermo;

    // Datas de controle
    private LocalDate ultimaDataEntrada;
    private LocalDate ultimaDataSaida;
}