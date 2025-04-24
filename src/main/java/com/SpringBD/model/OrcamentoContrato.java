package com.SpringBD.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrcamentoContrato {

    private int codOrcamento; // parte da chave primária
    private int numContrato;  // parte da chave primária e também UNIQUE

    private LocalDate dataFesta;
    private String diaSemana;
    private LocalTime horarioFesta;
    private BigDecimal valorInicial;
    private String localFesta;

    private int codTema;        // FK
    private int codCliente;     // FK
    private LocalDate dataAssinatura;
    private BigDecimal valorSinal;
    private int codFuncionario; // FK

    public OrcamentoContrato() {
    }

    public OrcamentoContrato(int codOrcamento, int numContrato, LocalDate dataFesta, String diaSemana, LocalTime horarioFesta,
                             BigDecimal valorInicial, String localFesta, int codTema, int codCliente,
                             LocalDate dataAssinatura, BigDecimal valorSinal, int codFuncionario) {
        this.codOrcamento = codOrcamento;
        this.numContrato = numContrato;
        this.dataFesta = dataFesta;
        this.diaSemana = diaSemana;
        this.horarioFesta = horarioFesta;
        this.valorInicial = valorInicial;
        this.localFesta = localFesta;
        this.codTema = codTema;
        this.codCliente = codCliente;
        this.dataAssinatura = dataAssinatura;
        this.valorSinal = valorSinal;
        this.codFuncionario = codFuncionario;
    }

    public int getCodOrcamento() {
        return codOrcamento;
    }

    public void setCodOrcamento(int codOrcamento) {
        this.codOrcamento = codOrcamento;
    }

    public int getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(int numContrato) {
        this.numContrato = numContrato;
    }

    public LocalDate getDataFesta() {
        return dataFesta;
    }

    public void setDataFesta(LocalDate dataFesta) {
        this.dataFesta = dataFesta;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioFesta() {
        return horarioFesta;
    }

    public void setHorarioFesta(LocalTime horarioFesta) {
        this.horarioFesta = horarioFesta;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getLocalFesta() {
        return localFesta;
    }

    public void setLocalFesta(String localFesta) {
        this.localFesta = localFesta;
    }

    public int getCodTema() {
        return codTema;
    }

    public void setCodTema(int codTema) {
        this.codTema = codTema;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public LocalDate getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(LocalDate dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public BigDecimal getValorSinal() {
        return valorSinal;
    }

    public void setValorSinal(BigDecimal valorSinal) {
        this.valorSinal = valorSinal;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }
}
