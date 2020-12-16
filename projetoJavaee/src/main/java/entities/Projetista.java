package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProjetistas",
                query = "SELECT p FROM Projetista p ORDER BY p.nome" // JPQL
        )
})
public class Projetista extends Pessoa {

    @OneToMany
    private Set<Projeto> projetos;

    public Projetista() {
        projetos = new LinkedHashSet<>();
    }

    public Projetista(String username, @Email String email, String nome, String contactoTelefonico) {
        super(username, email, nome, contactoTelefonico);
        projetos = new LinkedHashSet<>();
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void addProjeto(Projeto projeto) {
        if (this.projetos.contains(projeto)) {
            return;
        }

        this.projetos.add(projeto);

    }

    public void removeProjeto(Projeto projeto) {
        this.projetos.remove(projeto);

    }
}
