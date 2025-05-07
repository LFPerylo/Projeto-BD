package com.SpringBD.model;

public class Funcionario {
    private Integer codFuncionario;
    private String nome;
    private Integer supervisor;
    private String supervisorNome;  // novo campo

    // Getters e Setters
    public Integer getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(Integer codFuncionario) {
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

    public String getSupervisorNome() {
        return supervisorNome;
    }

    public void setSupervisorNome(String supervisorNome) {
        this.supervisorNome = supervisorNome;
    }
}
