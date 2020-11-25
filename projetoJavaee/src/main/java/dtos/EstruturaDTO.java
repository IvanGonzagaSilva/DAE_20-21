package dtos;

import java.io.Serializable;

public class EstruturaDTO implements Serializable {
    public int id;
    public String nome;
    public String tipoMaterial;
    public int numVaos;
    public int comprimentoVao;
    public int espacamentoVigas;
    public String parametrosCalculo;
    //public List<Material> materiais;

    public EstruturaDTO() {
        //this.materiais = new LinkedList<Material>();
    }

    public EstruturaDTO(int id, String nome, String tipoMaterial, int numVaos, int comprimentoVao, int espacamentoVigas, String parametrosCalculo) {
        this.id = id;
        this.nome = nome;
        this.tipoMaterial = tipoMaterial;
        this.numVaos = numVaos;
        this.comprimentoVao = comprimentoVao;
        this.espacamentoVigas = espacamentoVigas;
        this.parametrosCalculo = parametrosCalculo;
        //this.materiais = new LinkedList<Material>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public int getNumVaos() {
        return numVaos;
    }

    public void setNumVaos(int numVaos) {
        this.numVaos = numVaos;
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

    public String getParametrosCalculo() {
        return parametrosCalculo;
    }

    public void setParametrosCalculo(String parametrosCalculo) {
        this.parametrosCalculo = parametrosCalculo;
    }
}
