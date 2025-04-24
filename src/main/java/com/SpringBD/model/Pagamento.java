// Pagamento.java
package com.SpringBD.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pagamento {
    private int codPagamento;
    private BigDecimal valorFinal;
    private LocalDate dataPagamento;
    private String formaPagamento;
    private BigDecimal valorAcrescentado;
    private int codCliente;
    private int numContrato;

    public Pagamento() {}

    public Pagamento(int codPagamento, BigDecimal valorFinal, LocalDate dataPagamento, String formaPagamento,
                     BigDecimal valorAcrescentado, int codCliente, int numContrato) {
        this.codPagamento = codPagamento;
        this.valorFinal = valorFinal;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.valorAcrescentado = valorAcrescentado;
        this.codCliente = codCliente;
        this.numContrato = numContrato;
    }

    public int getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValorAcrescentado() {
        return valorAcrescentado;
    }

    public void setValorAcrescentado(BigDecimal valorAcrescentado) {
        this.valorAcrescentado = valorAcrescentado;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(int numContrato) {
        this.numContrato = numContrato;
    }
}