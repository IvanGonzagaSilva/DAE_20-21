package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@NamedQueries({
    @NamedQuery(
        name = "getAllAplicacoes",
        query = "SELECT a FROM Aplicacao a ORDER BY a.id" // JPQL
    )
})

@Entity
public class Aplicacao implements Serializable {

    @Id
    private int id;

    private String tipo;

    private int angulo;

    private int cargaPermanente;

    private int sobrecarga;

    private int categoriaSobrecarga;

    private int neve;

    private int ventoPressao;

    private int ventoSucao;

    public Aplicacao() {

    }

    // Aplicação de fachadas
    public Aplicacao(int id, String tipo, int cargaPermanente, int ventoPressao, int ventoSucao) {
        this.id = id;
        this.tipo = tipo;
        this.cargaPermanente = cargaPermanente;
        this.ventoPressao = ventoPressao;
        this.ventoSucao = ventoSucao;
    }

    // Aplicação de coberturas
    public Aplicacao(int id, String tipo, int angulo, int cargaPermanente, int sobrecarga, int neve, int ventoPressao, int ventoSucao) {
        this.id = id;
        this.tipo = tipo;
        this.angulo = angulo;
        this.cargaPermanente = cargaPermanente;
        this.sobrecarga = sobrecarga;
        this.neve = neve;
        this.ventoPressao = ventoPressao;
        this.ventoSucao = ventoSucao;
    }

    // Aplicação geral
    public Aplicacao(int id, String tipo, int angulo, int cargaPermanente, int sobrecarga, int categoriaSobrecarga, int neve, int ventoPressao, int ventoSucao) {
        this.id = id;
        this.tipo = tipo;
        this.angulo = angulo;
        this.cargaPermanente = cargaPermanente;
        this.sobrecarga = sobrecarga;
        this.categoriaSobrecarga = categoriaSobrecarga;
        this.neve = neve;
        this.ventoPressao = ventoPressao;
        this.ventoSucao = ventoSucao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getCargaPermanente() {
        return cargaPermanente;
    }

    public void setCargaPermanente(int cargaPermanente) {
        this.cargaPermanente = cargaPermanente;
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
