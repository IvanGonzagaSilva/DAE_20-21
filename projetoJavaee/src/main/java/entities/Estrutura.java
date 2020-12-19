package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "getAllEstruturas",
                query = "SELECT e FROM Estrutura e ORDER BY e.nome" // JPQL
        )
})

@Entity
public class Estrutura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    @ManyToOne
    private Geometria geometria; //Dimensões geométricas

    @ManyToOne
    private Aplicacao aplicacao; //Aplicação pretendida

    @ManyToOne
    private ParametrosCalculo parametrosCalculo; //Parametros de cálculo extra

    @OneToMany
    private Set<Material> materiais;

    @ManyToMany
    @JoinTable(name = "ESTRUTURAS_VARIANTES",
            joinColumns = @JoinColumn(name = "ESTRUTURA_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "VARIANTE_ID", referencedColumnName = "CODIGO"))
    private List<Variante> variantes; //Produtos resultantes da simulação

    public Estrutura() {
        this.variantes = new LinkedList<Variante>();
        this.materiais = new LinkedHashSet<>();
    }

    public Estrutura(String nome, Geometria geometria, Aplicacao aplicacao, ParametrosCalculo parametrosCalculo) {
        this();
        this.nome = nome;
        this.geometria = geometria;
        this.aplicacao = aplicacao;
        this.parametrosCalculo = parametrosCalculo;
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

    public Aplicacao getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;
    }

    public ParametrosCalculo getParametrosCalculo() {
        return parametrosCalculo;
    }

    public void setParametrosCalculo(ParametrosCalculo parametrosCalculo) {
        this.parametrosCalculo = parametrosCalculo;
    }

    public Set<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(Set<Material> materiais) {
        this.materiais = materiais;
    }

    public List<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<Variante> variantes) {
        this.variantes = variantes;
    }

    public void addMaterial(Material material) {
        this.materiais.add(material);
    }

    public void removeMaterial(Material material) {
        this.materiais.remove(material);
    }

    public void addVariante(Variante variante) {
        if (!this.variantes.contains(variante)) {
            this.variantes.add(variante);
        }
    }

    public void removeVariante(Variante variante) {
        this.variantes.remove(variante);
    }


}
