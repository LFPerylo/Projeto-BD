package com.SpringBD.model;

public class PessoaFisica extends Cliente {
    private String cpf;
    private String rg;

    public PessoaFisica() {
        super();
    }

    public PessoaFisica(String nome, String email, String rua, int numero, String bairro, String cidade, String estado, String cpf, String rg) {
        super(nome, email, rua, numero, bairro, cidade, estado);
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
