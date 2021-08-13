package com.example.graocafeapi.model;

import java.io.Serializable;

public class Characteristics implements Serializable {
    private String descricao;
    private String descricao_valor;

    public Characteristics(String descricao, String descricao_valor) {
        this.descricao = descricao;
        this.descricao_valor = descricao_valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao_valor() {
        return descricao_valor;
    }

    public void setDescricao_valor(String descricao_valor) {
        this.descricao_valor = descricao_valor;
    }
}
