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
    private LocalDate dataRetorno1;
    private LocalDate dataRetorno2;
    private LocalDate dataRetorno3;
    private String instituicaoEncaminhamento;
    private Boolean demandaEspontanea;
    private String nome;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private Integer idade;
    private String escolaridade;
    private String nacionalidade;
    private String naturalidade;
    private String estadoCivil;
    private String filhos;
    private String mae;
    private String pai;
    private String referenciasSociofamiliares;
    private Genero genero;
    private String telefone;
    private String cpf;
    private String rg;
    private String orgaoExpedidorRg;
    private String tituloEleitoral;
    private String carteiraTrabalho;
    private String certidaoNascimento;
    private String boletimOcorrencia;
    private String numeroNis;
    private Boolean cadUnico;
    private String cartaoSus;
    private String condicoesSaude;
    private String medicamentosEmUso;
    private String alergiasRestricoes;
    private String outrasAlergias;
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
        dto.setDataRetorno1(p.getDataRetorno1());
        dto.setDataRetorno2(p.getDataRetorno2());
        dto.setDataRetorno3(p.getDataRetorno3());
        dto.setInstituicaoEncaminhamento(p.getInstituicaoEncaminhamento());
        dto.setDemandaEspontanea(p.getDemandaEspontanea());
        dto.setNome(p.getNome());
        dto.setNomeSocial(p.getNomeSocial());
        dto.setDataNascimento(p.getDataNascimento());
        dto.setEscolaridade(p.getEscolaridade());
        dto.setNacionalidade(p.getNacionalidade());
        dto.setNaturalidade(p.getNaturalidade());
        dto.setEstadoCivil(p.getEstadoCivil());
        dto.setFilhos(p.getFilhos());
        dto.setMae(p.getMae());
        dto.setPai(p.getPai());
        dto.setReferenciasSociofamiliares(p.getReferenciasSociofamiliares());
        dto.setGenero(p.getGenero());
        dto.setTelefone(p.getTelefone());
        dto.setCpf(p.getCpf());
        dto.setRg(p.getRg());
        dto.setOrgaoExpedidorRg(p.getOrgaoExpedidorRg());
        dto.setTituloEleitoral(p.getTituloEleitoral());
        dto.setCarteiraTrabalho(p.getCarteiraTrabalho());
        dto.setCertidaoNascimento(p.getCertidaoNascimento());
        dto.setBoletimOcorrencia(p.getBoletimOcorrencia());
        dto.setNumeroNis(p.getNumeroNis());
        dto.setCadUnico(p.getCadUnico());
        dto.setCartaoSus(p.getCartaoSus());
        dto.setCondicoesSaude(p.getCondicoesSaude());
        dto.setMedicamentosEmUso(p.getMedicamentosEmUso());
        dto.setAlergiasRestricoes(p.getAlergiasRestricoes());
        dto.setOutrasAlergias(p.getOutrasAlergias());
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
