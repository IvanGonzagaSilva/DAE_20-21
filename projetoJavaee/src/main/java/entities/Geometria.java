package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@NamedQueries({
    @NamedQuery(
        name = "getAllGeometrias",
        query = "SELECT g FROM Geometria g ORDER BY g.id" // JPQL
    )
})

@Entity
public class Geometria implements Serializable {

    @Id
    private int id;

    private int numeroVaos;
    private int comprimentoVao;
    private int espacamentoVigas;

    @ManyToMany(mappedBy = "geometrias")
    private List<Familia> familia;

    public Geometria() {
        this.familia = new LinkedList<Familia>();
    }

    public Geometria(int id, int numeroVaos, int comprimentoVao, int espacamentoVigas) {
        this.id = id;
        this.numeroVaos = numeroVaos;
        this.comprimentoVao = comprimentoVao;
        this.espacamentoVigas = espacamentoVigas;
        this.familia = new LinkedList<Familia>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroVaos() {
        return numeroVaos;
    }

    public void setNumeroVaos(int numeroVaos) {
        this.numeroVaos = numeroVaos;
    }

    public int getComprimentoVao() {
        return comprimentoVao;
    }

    public void setComprimentoVao(int comprimentoVao) {
        this.comprimentoVao = comprimentoVao;
    }

    public int getEspacamentoVigas() {
        return espacamentoVigas;
    }

    public void setEspacamentoVigas(int espacamentoVigas) {
        this.espacamentoVigas = espacamentoVigas;
    }

    public List<Familia> getFamilia() {
        return familia;
    }

    public void setFamilia(List<Familia> familia) {
        this.familia = familia;
    }

    public void addFamilia(Familia fam) {
        if (! this.familia.contains(fam)) {
            familia.add(fam);
        }
    }

    public void removeFamilia(Familia fam) {
        familia.remove(fam);
    }
}
