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
    @JoinTable(name = "ESTRUTURAS_PRODUTOS",
            joinColumns = @JoinColumn(name = "ESTRUTURA_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUTO_ID", referencedColumnName = "ID"))
    private List<Produto> produtos; //Produtos resultantes da simulação

    public Estrutura() {
        this.produtos = new LinkedList<Produto>();
    }

    public Estrutura(String nome, Geometria geometria, Aplicacao aplicacao, ParametrosCalculo parametrosCalculo, Material material) {
        this.nome = nome;
        this.geometria = geometria;
        this.aplicacao = aplicacao;
        this.parametrosCalculo = parametrosCalculo;
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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(Produto produto) {
        if (!this.produtos.contains(produto)) {
            produtos.add(produto);
        }
    }

    public void removeProduto(Produto produto) {
        produtos.remove(produto);
    }
}
