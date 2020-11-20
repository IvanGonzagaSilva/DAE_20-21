package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

//projeto de estrutura metálica
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProjetos",
                query = "SELECT p FROM Projeto p ORDER BY p.nome" // JPQL
        )
})
public class Projeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    @ManyToOne
    @NotNull
    private Cliente cliente;

    @OneToMany
    private Set<Ficheiro> ficheiros;

    @OneToMany
    private Set<Estrutura> estruturas;


    public Projeto() {
        estruturas = new LinkedHashSet<>();
        ficheiros = new LinkedHashSet<>();
    }

    public Projeto(String nome, @NotNull Cliente cliente) {
        this();
        this.nome = nome;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Estrutura> getEstruturas() {
        return estruturas;
    }

    public void setEstruturas(Set<Estrutura> estruturas) {
        this.estruturas = estruturas;
    }

    public void addEstruturas(Estrutura estrutura){
        if(this.estruturas.contains(estrutura)){
            return;
        }
        this.estruturas.add(estrutura);
    }

    public void removeEstruturas(Estrutura estrutura){
        if(!this.estruturas.contains(estrutura)){
            return;
        }
        this.estruturas.remove(estrutura);
    }

    public void addFicheiro(Ficheiro ficheiro){
        if(this.ficheiros.contains(ficheiro)){
            return;
        }

        this.ficheiros.add(ficheiro);
    }

    public void removeFicheiro(Ficheiro ficheiro){

        if(!this.ficheiros.contains(ficheiro)){
            return;
        }

        this.ficheiros.remove(ficheiro);
    }
}
