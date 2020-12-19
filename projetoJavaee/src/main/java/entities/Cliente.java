package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
//O cliente é uma empresa.
@NamedQueries({
        @NamedQuery(
                name = "getAllClientes",
                query = "SELECT c FROM Cliente c ORDER BY c.nome" // JPQL
        ),
        @NamedQuery(
                name = "getAllClientProjetos",
                query = "SELECT c.projetos FROM Cliente c where c.username = :username" // JPQL
        ),
})
public class Cliente extends Pessoa {

    @OneToOne
    private PessoaDeContacto pc; // feito com apenas uma pessoa, podemos mudar para várias pessoasdecontacto

    private String morada;

    @ManyToMany
    @JoinTable(name = "cliente_projeto")
    private Set<Projeto> projetos;

    public Cliente() {
        projetos = new LinkedHashSet<>();
    }

    public Cliente(String nome, PessoaDeContacto pc, String morada, @Email String email, String username, String password, String contactoTelefonico) {
        super(username, email, nome, contactoTelefonico, password);
        projetos = new LinkedHashSet<>();
        this.pc = pc;
        this.morada = morada;
    }

    public PessoaDeContacto getPc() {
        return pc;
    }

    public void setPc(PessoaDeContacto pc) {
        this.pc = pc;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
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
        if (!this.projetos.contains(projeto)) {
            return;
        }

        this.projetos.remove(projeto);
    }
}
