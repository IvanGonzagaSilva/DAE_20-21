package dtos;

import entities.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class EstruturaDTO implements Serializable {
    private int id;
    private String nome;
    private GeometriaDTO geometria;
    private AplicacaoDTO aplicacao;
    private ParametrosCalculoDTO parametrosCalculo;
    private MaterialDTO material;
    private List<ProdutoDTO> produtos;

    public EstruturaDTO() {
        this.produtos = new LinkedList<ProdutoDTO>();
    }

    public EstruturaDTO(int id, String nome, GeometriaDTO geometria, AplicacaoDTO aplicacao, ParametrosCalculoDTO parametrosCalculo, MaterialDTO material, List<ProdutoDTO> produtos) {
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

    public GeometriaDTO getGeometria() {
        return geometria;
    }

    public void setGeometria(GeometriaDTO geometria) {
        this.geometria = geometria;
    }

    public AplicacaoDTO getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(AplicacaoDTO aplicacao) {
        this.aplicacao = aplicacao;
    }

    public ParametrosCalculoDTO getParametrosCalculo() {
        return parametrosCalculo;
    }

    public void setParametrosCalculo(ParametrosCalculoDTO parametrosCalculo) {
        this.parametrosCalculo = parametrosCalculo;
    }

    public MaterialDTO getMaterial() {
        return material;
    }

    public void setMaterial(MaterialDTO material) {
        this.material = material;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
