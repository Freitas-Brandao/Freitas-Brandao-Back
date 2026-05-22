package com.freitasbrandao.hospede;

import jakarta.persistence.Embeddable;

@Embeddable
public class Evolucao {

    private String data;
    private String descricao;
    private String responsavel;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
