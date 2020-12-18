package dtos;

import entities.Projeto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class projetistaDTO extends PessoaDTO {

    private Set<ProjetoDTO> projetos;

    public projetistaDTO() {
        projetos = new LinkedHashSet<>();
    }

    public Set<ProjetoDTO> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<ProjetoDTO> projetos) {
        this.projetos = projetos;
    }
}
