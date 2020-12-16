package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "PESSOAS")
public class Pessoa implements Serializable {

    @Id
    private String username;

    @Email
    private String email;

    private String nome;

    private String contactoTelefonico;

    public Pessoa() {
    }

    public Pessoa(String username, @Email String email, String nome, String contactoTelefonico) {
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.contactoTelefonico = contactoTelefonico;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContactoTelefonico() {
        return contactoTelefonico;
    }

    public void setContactoTelefonico(String contactoTelefonico) {
        this.contactoTelefonico = contactoTelefonico;
    }
}
