package com.SpringBD.model;

public class Tema {
    private int codTema;
    private String nome;
    private String descricao;

    public Tema() {}

    public Tema(int codTema, String nome, String descricao) {
        this.codTema = codTema;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getCodTema() {
        return codTema;
    }

    public void setCodTema(int codTema) {
        this.codTema = codTema;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
