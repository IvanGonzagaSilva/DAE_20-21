package entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllMateriais",
                query = "SELECT m FROM Material m ORDER BY m.tipoDeMaterial" // JPQL
        )
})
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Enumerated(EnumType.STRING)
    private TipoDeMaterial tipoDeMaterial;

    public Material() {
    }

    public Material(TipoDeMaterial tipoDeMaterial) {
        this.tipoDeMaterial = tipoDeMaterial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoDeMaterial getTipoDeMaterial() {
        return tipoDeMaterial;
    }

    public void setTipoDeMaterial(TipoDeMaterial tipoDeMaterial) {
        this.tipoDeMaterial = tipoDeMaterial;
    }
}
