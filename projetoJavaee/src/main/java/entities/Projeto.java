package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    private int id;
    private String nome;

    @ManyToOne
    @NotNull
    private Cliente cliente;
    //private Set<ficheiro> ficheiros;
    //private Set<Estrutura> estruturas;


    public Projeto() {
    }

    public Projeto(int id, String nome, Cliente cliente) {
        this.id = id;
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
}
