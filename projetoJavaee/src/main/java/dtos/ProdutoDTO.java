package dtos;

import entities.Material;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private int id;
    private String nome;
    private Material material;

    public ProdutoDTO() {
    }

    public ProdutoDTO(int id, String nome, Material material) {
        this.id = id;
        this.nome = nome;
        this.material = material;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
