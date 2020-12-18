package dtos;

import java.io.Serializable;

public class AplicacaoDTO implements Serializable {

    private int id;
    private int cargaPermanente;
    private int ventoPressao;
    private int ventoSucao;
    private int angulo;
    private int sobrecarga;
    private int categoriaSobrecarga;
    private int neve;
    private String tipo;

    public AplicacaoDTO() {
    }

    public AplicacaoDTO(int id, int cargaPermanente, int ventoPressao, int ventoSucao, int angulo, int sobrecarga, int categoriaSobrecarga, int neve, String tipo) {
        this.id = id;
        this.cargaPermanente = cargaPermanente;
        this.ventoPressao = ventoPressao;
        this.ventoSucao = ventoSucao;
        this.angulo = angulo;
        this.sobrecarga = sobrecarga;
        this.categoriaSobrecarga = categoriaSobrecarga;
        this.neve = neve;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getSobrecarga() {
        return sobrecarga;
    }

    public void setSobrecarga(int sobrecarga) {
        this.sobrecarga = sobrecarga;
    }

    public int getCategoriaSobrecarga() {
        return categoriaSobrecarga;
    }

    public void setCategoriaSobrecarga(int categoriaSobrecarga) {
        this.categoriaSobrecarga = categoriaSobrecarga;
    }

    public int getNeve() {
        return neve;
    }

    public void setNeve(int neve) {
        this.neve = neve;
    }
}
