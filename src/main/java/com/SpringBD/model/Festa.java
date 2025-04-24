package com.SpringBD.model;

import java.time.LocalTime;

public class Festa {
    private int codFesta;
    private int numContrato;
    private LocalTime horarioFim;
    private int codTema;

    public Festa() {}

    public Festa(int codFesta, int numContrato, LocalTime horarioFim, int codTema) {
        this.codFesta = codFesta;
        this.numContrato = numContrato;
        this.horarioFim = horarioFim;
        this.codTema = codTema;
    }

    public int getCodFesta() {
        return codFesta;
    }

    public void setCodFesta(int codFesta) {
        this.codFesta = codFesta;
    }

    public int getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(int numContrato) {
        this.numContrato = numContrato;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public int getCodTema() {
        return codTema;
    }

    public void setCodTema(int codTema) {
        this.codTema = codTema;
    }
}