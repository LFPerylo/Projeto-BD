// Contem.java
package com.SpringBD.model;

public class Contem {
    private int codFesta;
    private int codAniversariante;
    private int ano;

    public Contem() {}

    public Contem(int codFesta, int codAniversariante, int ano) {
        this.codFesta = codFesta;
        this.codAniversariante = codAniversariante;
        this.ano = ano;
    }

    public int getCodFesta() {
        return codFesta;
    }

    public void setCodFesta(int codFesta) {
        this.codFesta = codFesta;
    }

    public int getCodAniversariante() {
        return codAniversariante;
    }

    public void setCodAniversariante(int codAniversariante) {
        this.codAniversariante = codAniversariante;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
