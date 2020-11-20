package dtos;

import entities.TipoDeMaterial;

import java.io.Serializable;

public class MaterialDTO implements Serializable {
    private String id;

    private TipoDeMaterial tipoDeMaterial;

    public MaterialDTO(String id, TipoDeMaterial tipoDeMaterial) {
        this.id = id;
        this.tipoDeMaterial = tipoDeMaterial;
    }

    public MaterialDTO() {
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
