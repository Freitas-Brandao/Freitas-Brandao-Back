package com.freitasbrandao.dto;

import com.freitasbrandao.model.Genero;
import com.freitasbrandao.model.Pessoa;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

@Data
public class PessoaResponseDTO {

    private Long id;
    private String protocolo;
    private LocalDate dataAcolhimento;
    private LocalTime horaAcolhimento;
    private Boolean demandaEspontanea;
    private String nome;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private Integer idade;
    private String naturalidade;
    private Genero genero;
    private String telefone;
    private String cpf;
    private String rg;
    private String orgaoExpedidorRg;
    private String tituloEleitoral;
    private String carteiraTrabalho;
    private String certidaoNascimento;
    private String condicoesSaude;
    private String medicamentosEmUso;
    private String alergiasRestricoes;
    private Boolean usaSubstanciasPsicoativas;
    private String substanciasQuais;
    private String atividadesRealizadas;
    private String oficinasParticipadas;
    private String observacoes;
    private Boolean aceitouTermo;
    private LocalDate dataAssinaturaTermo;
    private LocalDateTime dataPrimeiroCadastro;
    private LocalDate ultimaDataEntrada;
    private LocalDate ultimaDataSaida;

    public static PessoaResponseDTO fromEntity(Pessoa p) {
        PessoaResponseDTO dto = new PessoaResponseDTO();
        dto.setId(p.getId());
        dto.setProtocolo(p.getProtocolo());
        dto.setDataAcolhimento(p.getDataAcolhimento());
        dto.setHoraAcolhimento(p.getHoraAcolhimento());
        dto.setDemandaEspontanea(p.getDemandaEspontanea());
        dto.setNome(p.getNome());
        dto.setNomeSocial(p.getNomeSocial());
        dto.setDataNascimento(p.getDataNascimento());
        dto.setNaturalidade(p.getNaturalidade());
        dto.setGenero(p.getGenero());
        dto.setTelefone(p.getTelefone());
        dto.setCpf(p.getCpf());
        dto.setRg(p.getRg());
        dto.setOrgaoExpedidorRg(p.getOrgaoExpedidorRg());
        dto.setTituloEleitoral(p.getTituloEleitoral());
        dto.setCarteiraTrabalho(p.getCarteiraTrabalho());
        dto.setCertidaoNascimento(p.getCertidaoNascimento());
        dto.setCondicoesSaude(p.getCondicoesSaude());
        dto.setMedicamentosEmUso(p.getMedicamentosEmUso());
        dto.setAlergiasRestricoes(p.getAlergiasRestricoes());
        dto.setUsaSubstanciasPsicoativas(p.getUsaSubstanciasPsicoativas());
        dto.setSubstanciasQuais(p.getSubstanciasQuais());
        dto.setAtividadesRealizadas(p.getAtividadesRealizadas());
        dto.setOficinasParticipadas(p.getOficinasParticipadas());
        dto.setObservacoes(p.getObservacoes());
        dto.setAceitouTermo(p.getAceitouTermo());
        dto.setDataAssinaturaTermo(p.getDataAssinaturaTermo());
        dto.setDataPrimeiroCadastro(p.getDataPrimeiroCadastro());
        dto.setUltimaDataEntrada(p.getUltimaDataEntrada());
        dto.setUltimaDataSaida(p.getUltimaDataSaida());

        // Calcula idade dinamicamente a partir da data de nascimento
        if (p.getDataNascimento() != null) {
            dto.setIdade(Period.between(p.getDataNascimento(), LocalDate.now()).getYears());
        }

        return dto;
    }
}