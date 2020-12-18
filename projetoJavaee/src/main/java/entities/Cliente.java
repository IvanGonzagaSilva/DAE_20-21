package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
//O cliente é uma empresa.
@NamedQueries({
        @NamedQuery(
                name = "getAllClientes",
                query = "SELECT c FROM Cliente c ORDER BY c.nome" // JPQL
        )
})
public class Cliente implements Serializable {

    private String nome;

    @OneToOne
    private PessoaDeContacto pc; // feito com apenas uma pessoa, podemos mudar para várias pessoasdecontacto

    private String morada;

    @Id
    @Email
    private String email;

    @OneToMany
    private Set<Projeto> projetos;

    public Cliente() {
        projetos = new LinkedHashSet<>();
    }

    public Cliente(String nome, PessoaDeContacto pc, String morada, @Email String email) {
        this();
        this.nome = nome;
        this.pc = pc;
        this.morada = morada;
        this.email = email;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
