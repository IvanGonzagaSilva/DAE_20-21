package dtos;

import entities.Familia;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class GeometriaDTO implements Serializable {

    private int id;
    private int numeroVaos;
    private int comprimentoVao;
    private int espacamentoVigas;
    private List<Familia> familias;

    public GeometriaDTO() {
        this.familias = new LinkedList<Familia>();
    }

    public GeometriaDTO(int id, int numeroVaos, int comprimentoVao, int espacamentoVigas, List<Familia> familias) {
        this.id = id;
        this.numeroVaos = numeroVaos;
        this.comprimentoVao = comprimentoVao;
        this.espacamentoVigas = espacamentoVigas;
        this.familias = familias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroVaos() {
        return numeroVaos;
    }

    public void setNumeroVaos(int numeroVaos) {
        this.numeroVaos = numeroVaos;
    }

    public int getComprimentoVao() {
        return comprimentoVao;
    }

    public void setComprimentoVao(int comprimentoVao) {
        this.comprimentoVao = comprimentoVao;
    }

    public int getEspacamentoVigas() {
        return espacamentoVigas;
    }

    public void setEspacamentoVigas(int espacamentoVigas) {
        this.espacamentoVigas = espacamentoVigas;
    }

    public List<Familia> getFamilias() {
        return familias;
    }

    public void setFamilias(List<Familia> familias) {
        this.familias = familias;
    }
}
