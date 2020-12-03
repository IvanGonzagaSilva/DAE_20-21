package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Produto {

    @Id
    private int id;

    private String nome;

    @ManyToOne
    private Material material;

    @ManyToMany(mappedBy = "produtos")
    private List<Estrutura> estruturas;

    public Produto() {
        this.estruturas =  new LinkedList<Estrutura>();
    }

    public Produto(int id, String nome, Material material) {
        this.id = id;
        this.nome = nome;
        this.material = material;
        this.estruturas =  new LinkedList<Estrutura>();
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

    public List<Estrutura> getEstruturas() {
        return estruturas;
    }

    public void setEstruturas(List<Estrutura> estruturas) {
        this.estruturas = estruturas;
    }
}
