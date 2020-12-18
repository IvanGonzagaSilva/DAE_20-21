package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Estrutura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @OneToMany
    private Set<Material> materiais;

    public Estrutura() {
        materiais = new LinkedHashSet<>();
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

    public void addMaterial(Material material){
        this.materiais.add(material);
    }

    public void removeMaterial(Material material){
        this.materiais.remove(material);
    }
}
