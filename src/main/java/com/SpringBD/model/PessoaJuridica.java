package com.SpringBD.model;

public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String inscricaoEstadual;
    private String razaoSocial;

    public PessoaJuridica() {
        super();
    }

    public PessoaJuridica(String nome, String email, String rua, int numero, String bairro, String cidade, String estado, String cnpj, String inscricaoEstadual, String razaoSocial) {
        super(nome, email, rua, numero, bairro, cidade, estado);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.razaoSocial = razaoSocial;
    }

    public PessoaJuridica(Cliente cliente) {
        super(cliente.getCodCliente(), cliente.getNome(), cliente.getEmail(), cliente.getRua(),
                cliente.getNumero(), cliente.getBairro(), cliente.getCidade(), cliente.getEstado(),
                null, null,
                cliente.getCnpj(), cliente.getInscricaoEstadual(), cliente.getRazaoSocial());
        this.cnpj = cliente.getCnpj();
        this.inscricaoEstadual = cliente.getInscricaoEstadual();
        this.razaoSocial = cliente.getRazaoSocial();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
