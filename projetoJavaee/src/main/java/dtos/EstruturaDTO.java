package dtos;

import entities.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EstruturaDTO implements Serializable {
    private int id;
    private String nome;
    private GeometriaDTO geometria;
    private AplicacaoDTO aplicacao;
    private ParametrosCalculoDTO parametrosCalculo;
    private Set<MaterialDTO> materiais;
    private List<VarianteDTO> variantes;

    public EstruturaDTO() {
        this.variantes = new LinkedList<VarianteDTO>();
        this.materiais = new LinkedHashSet<>();
    }

    public EstruturaDTO(int id, String nome, GeometriaDTO geometria, AplicacaoDTO aplicacao, ParametrosCalculoDTO parametrosCalculo, Set<MaterialDTO> materiais, List<VarianteDTO> variantes) {
        this.id = id;
        this.nome = nome;
        this.geometria = geometria;
        this.aplicacao = aplicacao;
        this.parametrosCalculo = parametrosCalculo;
        this.materiais = materiais;
        this.variantes = variantes;
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

    public Set<MaterialDTO> getMateriais() {
        return materiais;
    }

    public void setMateriais(Set<MaterialDTO> materiais) {
        this.materiais = materiais;
    }

    public List<VarianteDTO> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<VarianteDTO> variantes) {
        this.variantes = variantes;
    }
}
