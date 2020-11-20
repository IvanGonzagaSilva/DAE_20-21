package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Estrutura implements Serializable {

    @Id
    private String id;

    @OneToMany
    private Set<Material> materiais;

    public Estrutura() {
        materiais = new LinkedHashSet<>();
    }

    public Estrutura(String id, Set<Material> materiais) {
        this();
        this.id = id;
        this.materiais = materiais;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Set<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(Set<Material> materiais) {
        this.materiais = materiais;
    }
}
