package entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
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
    private PessoaDeContacto pc; // pode vir a mudar, o professor vai falar com o ricardo martinho para decidir de é one to one ou one to many.

    private String morada;

    @Id
    @Email
    private String email;

    public Cliente() {
    }

    public Cliente(String nome, PessoaDeContacto pc, String morada, @Email String email) {
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
}
