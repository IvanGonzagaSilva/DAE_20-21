package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@NamedQueries({
    @NamedQuery(
        name = "getAllFamilias",
        query = "SELECT f FROM Familia f ORDER BY f.id" // JPQL
    )
})

@Entity
public class Familia implements Serializable {
    @Id
    private int id;
    private String nome;

    public Familia() {
    }

    public Familia(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
}
