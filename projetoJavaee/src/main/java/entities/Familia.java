package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
    @ManyToMany(mappedBy = "familias")
    private List<Geometria> geometrias;

    public Familia() {
        this.geometrias = new LinkedList<Geometria>();
    }

    public Familia(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.geometrias = new LinkedList<Geometria>();
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

    public List<Geometria> getGeometrias() {
        return geometrias;
    }

    public void setGeometrias(List<Geometria> geometrias) {
        this.geometrias = geometrias;
    }
}
