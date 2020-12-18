package dtos;

import entities.TipoDeMaterial;

import java.io.Serializable;

public class MaterialDTO implements Serializable {

    private TipoDeMaterial tipoDeMaterial;

    public MaterialDTO(TipoDeMaterial tipoDeMaterial) {
        this.tipoDeMaterial = tipoDeMaterial;
    }

    public MaterialDTO() {
    }

    public TipoDeMaterial getTipoDeMaterial() {
        return tipoDeMaterial;
    }

    public void setTipoDeMaterial(TipoDeMaterial tipoDeMaterial) {
        this.tipoDeMaterial = tipoDeMaterial;
    }
}
