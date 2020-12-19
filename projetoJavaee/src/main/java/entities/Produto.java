package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "getAllProdutos",
                query = "SELECT p FROM Produto p ORDER BY p.nome" // JPQL
        )
})

@Entity
public class Produto implements Serializable {

    @Id
    private String nome;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
    private List<Variante> variantes;

    public Produto() {
        variantes = new LinkedList<>();
    }

    public Produto(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public List<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<Variante> especimen) {
        this.variantes = especimen;
    }

    public void addVariante(Variante s) {
        variantes.add(s);
    }

    public void removeVariante(Variante s) {
        variantes.remove(s);
    }

}
