package dtos;

import entities.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class EstruturaDTO implements Serializable {
    private int id;
    private String nome;
    private Geometria geometria;
    private Aplicacao aplicacao;
    private ParametrosCalculo parametrosCalculo;
    private Material material;
    private List<Produto> produtos;

    public EstruturaDTO() {
        this.produtos = new LinkedList<Produto>();
    }

    public EstruturaDTO(int id, String nome, Geometria geometria, Aplicacao aplicacao, ParametrosCalculo parametrosCalculo, Material material, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.geometria = geometria;
        this.aplicacao = aplicacao;
        this.parametrosCalculo = parametrosCalculo;
        this.material = material;
        this.produtos = produtos;
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

    public Geometria getGeometria() {
        return geometria;
    }

    public void setGeometria(Geometria geometria) {
        this.geometria = geometria;
    }

    public Aplicacao getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;
    }

    public ParametrosCalculo getParametrosCalculo() {
        return parametrosCalculo;
    }

    public void setParametrosCalculo(ParametrosCalculo parametrosCalculo) {
        this.parametrosCalculo = parametrosCalculo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
