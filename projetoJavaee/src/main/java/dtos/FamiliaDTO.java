package dtos;

import java.io.Serializable;

public class FamiliaDTO implements Serializable {
    private String nome;

    public FamiliaDTO() {
    }

    public FamiliaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
