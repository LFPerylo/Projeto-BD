package com.SpringBD.model;

public class Funcionario {
    private int codFuncionario;
    private String nome;
    private Integer supervisor; // Representa o Cod_Funcionario do supervisor, pode ser null

    public Funcionario() {
    }

    public Funcionario(int codFuncionario, String nome, Integer supervisor) {
        this.codFuncionario = codFuncionario;
        this.nome = nome;
        this.supervisor = supervisor;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
    }
}
