package dtos;

import java.util.LinkedHashSet;
import java.util.Set;

public class ProjetistaDTO extends PessoaDTO {

    private Set<ProjetoDTO> projetos;

    public ProjetistaDTO() {
        projetos = new LinkedHashSet<>();
    }

    public ProjetistaDTO(String username, String email, String nome, String contactoTelefonico, String password, Set<ProjetoDTO> projetos) {
        super(username, email, nome, contactoTelefonico, password);
        this.projetos = projetos;
    }

    public Set<ProjetoDTO> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<ProjetoDTO> projetos) {
        this.projetos = projetos;
    }
}
