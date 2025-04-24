// Modelo: Aniversariante.java
package com.SpringBD.model;

import java.time.LocalDate;

public class Aniversariante {
    private int codAniversariante;
    private String nome;
    private int idade;
    private LocalDate dataNascimento;

    public Aniversariante() {}

    public Aniversariante(int codAniversariante, String nome, int idade, LocalDate dataNascimento) {
        this.codAniversariante = codAniversariante;
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
    }

    public int getCodAniversariante() {
        return codAniversariante;
    }

    public void setCodAniversariante(int codAniversariante) {
        this.codAniversariante = codAniversariante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
