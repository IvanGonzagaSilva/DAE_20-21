package dtos;

import entities.Variante;

import java.io.Serializable;
import java.util.List;

public class ProdutoDTO implements Serializable {

    private String nome;
    private List<VarianteDTO> variantes;

    public ProdutoDTO() {
    }

    public ProdutoDTO(String nome, List<VarianteDTO> variantes) {
        this.nome = nome;
        this.variantes = variantes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<VarianteDTO> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<VarianteDTO> variantes) {
        this.variantes = variantes;
    }
}
