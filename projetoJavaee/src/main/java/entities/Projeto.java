package entities;

import javax.persistence.*;
import javax.validation.Constraint;
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
    @NotNull
    private int id;

    private String nome;


    @OneToMany
    private Set<Ficheiro> ficheiros;

    @OneToMany
    private Set<Estrutura> estruturas;

    @ManyToOne
    private Projetista projetista;

  @ManyToMany(mappedBy = "projetos")
  private Set<Cliente> clientes;


    public Projeto() {
        estruturas = new LinkedHashSet<>();
        ficheiros = new LinkedHashSet<>();
        clientes = new LinkedHashSet<>();
    }

    public Projeto(String nome, Projetista projetista) {
        this();
        this.nome = nome;
        this.projetista = projetista;
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

  public void removeCliente(Cliente cliente){
    if(!this.clientes.contains(cliente)){
      return;
    }
    this.clientes.remove(cliente);
  }

  public void addCliente(Cliente cliente){
    if(this.clientes.contains(cliente)){
      return;
    }

    this.clientes.add(cliente);
  }

  public Set<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(Set<Cliente> clientes) {
    this.clientes = clientes;
  }

  public void removeFicheiro(Ficheiro ficheiro){

        if(!this.ficheiros.contains(ficheiro)){
            return;
        }

        this.ficheiros.remove(ficheiro);
    }

    public Set<Ficheiro> getFicheiros() {
        return ficheiros;
    }

    public void setFicheiros(Set<Ficheiro> ficheiros) {
        this.ficheiros = ficheiros;
    }

    public Projetista getProjetista() {
        return projetista;
    }

    public void setProjetista(Projetista projetista) {
        this.projetista = projetista;
    }
}
