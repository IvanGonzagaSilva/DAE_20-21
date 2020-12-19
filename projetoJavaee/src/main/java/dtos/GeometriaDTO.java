package dtos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class GeometriaDTO implements Serializable {

    private int id;
    private int numeroVaos;
    private int comprimentoVao;
    private int espacamentoVigas;
    private List<FamiliaDTO> familias;

    public GeometriaDTO() {
        this.familias = new LinkedList<FamiliaDTO>();
    }

    public GeometriaDTO(int id, int numeroVaos, int comprimentoVao, int espacamentoVigas, List<FamiliaDTO> familias) {
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

    public List<FamiliaDTO> getFamilias() {
        return familias;
    }

    public void setFamilias(List<FamiliaDTO> familias) {
        this.familias = familias;
    }
}
