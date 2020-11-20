package dtos;

import java.io.Serializable;
import java.util.Set;

public class EstruturaDTO implements Serializable {

    private String id;

    private Set<MaterialDTO> materiais;

    public EstruturaDTO(String id, Set<MaterialDTO> materiais) {
        this.id = id;
        this.materiais = materiais;
    }

    public EstruturaDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<MaterialDTO> getMateriais() {
        return materiais;
    }

    public void setMateriais(Set<MaterialDTO> materiais) {
        this.materiais = materiais;
    }
}
