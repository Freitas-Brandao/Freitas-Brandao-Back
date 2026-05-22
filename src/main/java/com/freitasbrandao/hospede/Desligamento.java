package com.freitasbrandao.hospede;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Desligamento {

    private String id;
    private Integer numero;
    private String data;
    private String motivo;
    private Boolean devolveuRoupas;
    private Boolean levouDocumentos;
    private Boolean temLesoes;
    private String assinaturaUsuario;
    private String tecnico;
    private String dataTecnico;
    private String observacoes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Boolean getDevolveuRoupas() {
        return devolveuRoupas;
    }

    public void setDevolveuRoupas(Boolean devolveuRoupas) {
        this.devolveuRoupas = devolveuRoupas;
    }

    public Boolean getLevouDocumentos() {
        return levouDocumentos;
    }

    public void setLevouDocumentos(Boolean levouDocumentos) {
        this.levouDocumentos = levouDocumentos;
    }

    public Boolean getTemLesoes() {
        return temLesoes;
    }

    public void setTemLesoes(Boolean temLesoes) {
        this.temLesoes = temLesoes;
    }

    public String getAssinaturaUsuario() {
        return assinaturaUsuario;
    }

    public void setAssinaturaUsuario(String assinaturaUsuario) {
        this.assinaturaUsuario = assinaturaUsuario;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getDataTecnico() {
        return dataTecnico;
    }

    public void setDataTecnico(String dataTecnico) {
        this.dataTecnico = dataTecnico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
