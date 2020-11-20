package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Material implements Serializable {

    @Id
    private String id;

    private TipoDeMaterial tipoDeMaterial;

    public Material() {
    }

    public Material(String id, TipoDeMaterial tipoDeMaterial) {
        this.id = id;
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
