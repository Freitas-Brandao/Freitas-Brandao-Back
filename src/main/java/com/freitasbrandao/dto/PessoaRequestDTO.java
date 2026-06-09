package com.freitasbrandao.dto;

import com.freitasbrandao.model.Genero;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PessoaRequestDTO {

    // Acolhimento
    private LocalDate dataAcolhimento;

    private LocalTime horaAcolhimento;

    private LocalDate dataRetorno1;
    private LocalDate dataRetorno2;
    private LocalDate dataRetorno3;
    private String instituicaoEncaminhamento;

    private Boolean demandaEspontanea = false;

    // Dados pessoais
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String nomeSocial;

    private LocalDate dataNascimento;

    private String escolaridade;
    private String nacionalidade;
    private String naturalidade;
    private String estadoCivil;
    private String filhos;
    private String mae;
    private String pai;
    private String referenciasSociofamiliares;

    @NotNull(message = "Gênero é obrigatório")
    private Genero genero;

    @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}$",
            message = "Telefone inválido")
    private String telefone;

    // Documentação
    @Pattern(
            regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$",
            message = "CPF inválido. Use 11 dígitos numéricos ou o formato 000.000.000-00"
    )
    private String cpf;

    private String rg;
    private String orgaoExpedidorRg;
    private String tituloEleitoral;
    private String carteiraTrabalho;
    private String certidaoNascimento;
    private String boletimOcorrencia;
    private String numeroNis;
    private Boolean cadUnico = false;
    private String cartaoSus;

    // Saúde
    private String condicoesSaude;
    private String medicamentosEmUso;
    private String alergiasRestricoes;
    private String outrasAlergias;
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
