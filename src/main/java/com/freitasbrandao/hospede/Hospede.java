package com.freitasbrandao.hospede;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hospede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String protocolo;
    private String dataAcolhimento;
    private String dataRetorno;
    private String demandaEspontanea;
    private String motivoEntrada;
    private String instituicaoEncaminhamento;
    private String nome;
    private String dataNascimento;
    private Integer idade;
    private String escolaridade;
    private String nacionalidade;
    private String naturalidade;
    private String estadoCivil;
    private String filhos;
    private String referenciasFamiliares;
    private String mae;
    private String pai;
    private String rg;
    private String cpf;
    private String tituloEleitoral;
    private String carteiraTrabalho;
    private String certidaoNascimento;
    private String boletimOcorrencia;
    private Boolean recebeBolsaFamilia;
    private Boolean recebeBpc;
    private Boolean recebeAposentadoria;
    private Boolean recebeCadUnico;
    private String numeroNis;
    private String outrosBeneficios;
    private Boolean problemaSaude;
    private String problemaSaudeQual;
    private Boolean alergia;
    private String alergiaQual;
    private Boolean medicamentoControlado;
    private String medicamentoQual;
    private Boolean usaSpa;
    private String usaSpaQual;
    private Boolean outraAlergia;
    private String outraAlergiaQual;
    private String cartaoSus;
    private String atividadeProfissional;
    private String enderecoReferencia;
    private String telefoneContato;
    private String observacoes;
    private Boolean aceitouTermo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hospede_desligamentos", joinColumns = @JoinColumn(name = "hospede_id"))
    private List<Desligamento> desligamentos = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hospede_evolucoes", joinColumns = @JoinColumn(name = "hospede_id"))
    private List<Evolucao> evolucoes = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hospede_encaminhamentos", joinColumns = @JoinColumn(name = "hospede_id"))
    private List<Encaminhamento> encaminhamentos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getDataAcolhimento() {
        return dataAcolhimento;
    }

    public void setDataAcolhimento(String dataAcolhimento) {
        this.dataAcolhimento = dataAcolhimento;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getDemandaEspontanea() {
        return demandaEspontanea;
    }

    public void setDemandaEspontanea(String demandaEspontanea) {
        this.demandaEspontanea = demandaEspontanea;
    }

    public String getMotivoEntrada() {
        return motivoEntrada;
    }

    public void setMotivoEntrada(String motivoEntrada) {
        this.motivoEntrada = motivoEntrada;
    }

    public String getInstituicaoEncaminhamento() {
        return instituicaoEncaminhamento;
    }

    public void setInstituicaoEncaminhamento(String instituicaoEncaminhamento) {
        this.instituicaoEncaminhamento = instituicaoEncaminhamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFilhos() {
        return filhos;
    }

    public void setFilhos(String filhos) {
        this.filhos = filhos;
    }

    public String getReferenciasFamiliares() {
        return referenciasFamiliares;
    }

    public void setReferenciasFamiliares(String referenciasFamiliares) {
        this.referenciasFamiliares = referenciasFamiliares;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTituloEleitoral() {
        return tituloEleitoral;
    }

    public void setTituloEleitoral(String tituloEleitoral) {
        this.tituloEleitoral = tituloEleitoral;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(String carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public String getCertidaoNascimento() {
        return certidaoNascimento;
    }

    public void setCertidaoNascimento(String certidaoNascimento) {
        this.certidaoNascimento = certidaoNascimento;
    }

    public String getBoletimOcorrencia() {
        return boletimOcorrencia;
    }

    public void setBoletimOcorrencia(String boletimOcorrencia) {
        this.boletimOcorrencia = boletimOcorrencia;
    }

    public Boolean getRecebeBolsaFamilia() {
        return recebeBolsaFamilia;
    }

    public void setRecebeBolsaFamilia(Boolean recebeBolsaFamilia) {
        this.recebeBolsaFamilia = recebeBolsaFamilia;
    }

    public Boolean getRecebeBpc() {
        return recebeBpc;
    }

    public void setRecebeBpc(Boolean recebeBpc) {
        this.recebeBpc = recebeBpc;
    }

    public Boolean getRecebeAposentadoria() {
        return recebeAposentadoria;
    }

    public void setRecebeAposentadoria(Boolean recebeAposentadoria) {
        this.recebeAposentadoria = recebeAposentadoria;
    }

    public Boolean getRecebeCadUnico() {
        return recebeCadUnico;
    }

    public void setRecebeCadUnico(Boolean recebeCadUnico) {
        this.recebeCadUnico = recebeCadUnico;
    }

    public String getNumeroNis() {
        return numeroNis;
    }

    public void setNumeroNis(String numeroNis) {
        this.numeroNis = numeroNis;
    }

    public String getOutrosBeneficios() {
        return outrosBeneficios;
    }

    public void setOutrosBeneficios(String outrosBeneficios) {
        this.outrosBeneficios = outrosBeneficios;
    }

    public Boolean getProblemaSaude() {
        return problemaSaude;
    }

    public void setProblemaSaude(Boolean problemaSaude) {
        this.problemaSaude = problemaSaude;
    }

    public String getProblemaSaudeQual() {
        return problemaSaudeQual;
    }

    public void setProblemaSaudeQual(String problemaSaudeQual) {
        this.problemaSaudeQual = problemaSaudeQual;
    }

    public Boolean getAlergia() {
        return alergia;
    }

    public void setAlergia(Boolean alergia) {
        this.alergia = alergia;
    }

    public String getAlergiaQual() {
        return alergiaQual;
    }

    public void setAlergiaQual(String alergiaQual) {
        this.alergiaQual = alergiaQual;
    }

    public Boolean getMedicamentoControlado() {
        return medicamentoControlado;
    }

    public void setMedicamentoControlado(Boolean medicamentoControlado) {
        this.medicamentoControlado = medicamentoControlado;
    }

    public String getMedicamentoQual() {
        return medicamentoQual;
    }

    public void setMedicamentoQual(String medicamentoQual) {
        this.medicamentoQual = medicamentoQual;
    }

    public Boolean getUsaSpa() {
        return usaSpa;
    }

    public void setUsaSpa(Boolean usaSpa) {
        this.usaSpa = usaSpa;
    }

    public String getUsaSpaQual() {
        return usaSpaQual;
    }

    public void setUsaSpaQual(String usaSpaQual) {
        this.usaSpaQual = usaSpaQual;
    }

    public Boolean getOutraAlergia() {
        return outraAlergia;
    }

    public void setOutraAlergia(Boolean outraAlergia) {
        this.outraAlergia = outraAlergia;
    }

    public String getOutraAlergiaQual() {
        return outraAlergiaQual;
    }

    public void setOutraAlergiaQual(String outraAlergiaQual) {
        this.outraAlergiaQual = outraAlergiaQual;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public String getAtividadeProfissional() {
        return atividadeProfissional;
    }

    public void setAtividadeProfissional(String atividadeProfissional) {
        this.atividadeProfissional = atividadeProfissional;
    }

    public String getEnderecoReferencia() {
        return enderecoReferencia;
    }

    public void setEnderecoReferencia(String enderecoReferencia) {
        this.enderecoReferencia = enderecoReferencia;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getAceitouTermo() {
        return aceitouTermo;
    }

    public void setAceitouTermo(Boolean aceitouTermo) {
        this.aceitouTermo = aceitouTermo;
    }

    public List<Desligamento> getDesligamentos() {
        return desligamentos;
    }

    public void setDesligamentos(List<Desligamento> desligamentos) {
        this.desligamentos = desligamentos;
    }

    public List<Evolucao> getEvolucoes() {
        return evolucoes;
    }

    public void setEvolucoes(List<Evolucao> evolucoes) {
        this.evolucoes = evolucoes;
    }

    public List<Encaminhamento> getEncaminhamentos() {
        return encaminhamentos;
    }

    public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
        this.encaminhamentos = encaminhamentos;
    }
}
