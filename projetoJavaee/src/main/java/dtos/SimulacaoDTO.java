package dtos;

import java.io.Serializable;

public class SimulacaoDTO implements Serializable {
    private int nb;
    private int LVao;
    private int q;
    private int codVariante;

    public SimulacaoDTO() {
    }

    public SimulacaoDTO(int nb, int LVao, int q, int codVariante) {
        this.nb = nb;
        this.LVao = LVao;
        this.q = q;
        this.codVariante = codVariante;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getLVao() {
        return LVao;
    }

    public void setLVao(int LVao) {
        this.LVao = LVao;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getCodVariante() {
        return codVariante;
    }

    public void setCodVariante(int codVariante) {
        this.codVariante = codVariante;
    }
}
