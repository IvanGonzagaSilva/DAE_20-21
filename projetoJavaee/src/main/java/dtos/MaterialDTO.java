package dtos;


import entities.TipoDeMaterial;

import java.io.Serializable;

public class MaterialDTO implements Serializable {

    private int id;
    private TipoDeMaterial tipoDeMaterial;

    public MaterialDTO(int id, TipoDeMaterial tipoDeMaterial) {
        this.id = id;
        this.tipoDeMaterial = tipoDeMaterial;
    }

    public MaterialDTO() {
    }

    public int getId() {
        return id;
    }

    public TipoDeMaterial getTipoDeMaterial() {
        return tipoDeMaterial;
    }

    public void setTipoDeMaterial(TipoDeMaterial tipoDeMaterial) {
        this.tipoDeMaterial = tipoDeMaterial;
    }
}
