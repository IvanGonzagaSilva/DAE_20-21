package dtos;

import java.io.Serializable;

public class AplicacaoDTO implements Serializable {

    private int id;
    private int cargaPermanente;
    private int ventoPressao;
    private int ventoSucao;

    public AplicacaoDTO() {
    }

    public AplicacaoDTO(int id, int cargaPermanente, int ventoPressao, int ventoSucao) {
        this.id = id;
        this.cargaPermanente = cargaPermanente;
        this.ventoPressao = ventoPressao;
        this.ventoSucao = ventoSucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
