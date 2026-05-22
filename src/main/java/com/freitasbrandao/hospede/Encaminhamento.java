package com.freitasbrandao.hospede;

import jakarta.persistence.Embeddable;

@Embeddable
public class Encaminhamento {

    private String data;
    private String destino;
    private String observacoes;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
