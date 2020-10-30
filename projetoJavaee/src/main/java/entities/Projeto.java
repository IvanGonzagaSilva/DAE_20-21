package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

//projeto de estrutura metálica
@Entity
public class Projeto implements Serializable {

    @Id
    private int id;
    private String nome;

    @ManyToOne
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

}
