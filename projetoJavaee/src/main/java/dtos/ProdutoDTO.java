package dtos;

import entities.Material;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private int id;
    private String nome;
    private MaterialDTO material;

    public ProdutoDTO() {
    }

    public ProdutoDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
