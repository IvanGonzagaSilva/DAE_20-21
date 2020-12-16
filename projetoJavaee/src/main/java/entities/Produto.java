package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@NamedQueries({
    @NamedQuery(
        name = "getAllProdutos",
        query = "SELECT p FROM Produto p ORDER BY p.id" // JPQL
    )
})

@Entity
public class Produto implements Serializable {

    @Id
    private int id;

    private String nome;

    @ManyToMany(mappedBy = "produtos")
    private List<Estrutura> estruturas;

    public Produto() {
        this.estruturas = new LinkedList<Estrutura>();
    }

    public Produto(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.estruturas = new LinkedList<Estrutura>();
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

    public List<Estrutura> getEstruturas() {
        return estruturas;
    }

    public void setEstruturas(List<Estrutura> estruturas) {
        this.estruturas = estruturas;
    }
}
