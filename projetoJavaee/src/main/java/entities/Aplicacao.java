package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "APLICACOES")
public class Aplicacao implements Serializable {

    @Id
    private int id;

    private int cargaPermanente;

    private int ventoPressao;

    private int ventoSucao;

    public Aplicacao() {

    }

    public Aplicacao(int id, int cargaPermanente, int ventoPressao, int ventoSucao) {
        this.id = id;
        this.cargaPermanente = cargaPermanente;
        this.ventoPressao = ventoPressao;
        this.ventoSucao = ventoSucao;
    }

    public int getId() {
        return id;
    }

    public int getCargaPermanente() {
        return cargaPermanente;
    }

    public void setCargaPermanente(int cargaPermanente) {
        this.cargaPermanente = cargaPermanente;
    }

    public int getVentoPressao() {
        return ventoPressao;
    }

    public void setVentoPressao(int ventoPressao) {
        this.ventoPressao = ventoPressao;
    }

    public int getVentoSucao() {
        return ventoSucao;
    }

    public void setVentoSucao(int ventoSucao) {
        this.ventoSucao = ventoSucao;
    }
}
