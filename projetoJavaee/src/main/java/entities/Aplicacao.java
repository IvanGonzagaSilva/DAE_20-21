package entities;

import javax.persistence.*;
import java.io.Serializable;

@NamedQueries({
    @NamedQuery(
        name = "getAllAplicacoes",
        query = "SELECT a FROM Aplicacao a ORDER BY a.id" // JPQL
    )
})

@Entity
@Table(name = "APLICACOES")
public class Aplicacao implements Serializable {

    @Id
    private int id;

    private String tipo;

    private int cargaPermanente;

    private int ventoPressao;

    private int ventoSucao;

    public Aplicacao() {

    }

    public Aplicacao(int id, int cargaPermanente, int ventoPressao, int ventoSucao, String tipo) {
        this.id = id;
        this.cargaPermanente = cargaPermanente;
        this.ventoPressao = ventoPressao;
        this.ventoSucao = ventoSucao;
        this.tipo = tipo;
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
