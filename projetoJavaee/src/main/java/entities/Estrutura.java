package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@NamedQueries({
    @NamedQuery(
        name = "getAllEstruturas",
        query = "SELECT e FROM Estrutura e ORDER BY e.nome" // JPQL
    )
})

@Entity
public class Estrutura implements Serializable {
    @Id
    private int id;

    private String nome;

    @ManyToOne
    private Geometria geometria; //Dimensões geométricas

    @ManyToOne
    private Material material; //Material utilizado

    @ManyToMany(mappedBy = "estruturas")
    private List<Produto> produtos; //Produtos resultantes da simulação

    public Estrutura() {
        this.produtos = new LinkedList<Produto>();
    }

    public Estrutura(int id, String nome, Geometria geometria, Material material) {
        this.id = id;
        this.nome = nome;
        this.geometria = geometria;
        this.material = material;
        this.produtos = new LinkedList<Produto>();
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

    public void addProduto(Produto produto) {
        if (! this.produtos.contains(produto)) {
            produtos.add(produto);
        }
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);
    }
}
