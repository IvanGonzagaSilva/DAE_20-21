package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
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

    public Projetista(String username, @Email String email, String nome, String contactoTelefonico, String password) {
        super(username, email, nome, contactoTelefonico, password);
        projetos = new LinkedHashSet<>();
    }

    public Set<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(Set<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void addProjeto(Projeto projeto){
        if(this.projetos.contains(projeto)){
            return;
        }

        this.projetos.add(projeto);

    }

    public void removeProjeto(Projeto projeto){
        if(this.projetos.contains(projeto)){
            this.projetos.remove(projeto);
        }

    }
}
