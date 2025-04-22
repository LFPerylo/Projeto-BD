package com.SpringBD.model;

public class TelefonesCliente {
    private int codCliente;
    private String telefone;

    public TelefonesCliente() {}

    public TelefonesCliente(int codCliente, String telefone) {
        this.codCliente = codCliente;
        this.telefone = telefone;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}