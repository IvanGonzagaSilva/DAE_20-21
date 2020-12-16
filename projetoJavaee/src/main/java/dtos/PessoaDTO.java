package dtos;

import java.io.Serializable;

public class PessoaDTO implements Serializable {
    private String username;

    private String email;

    private String nome;

    private String contactoTelefonico;

    public PessoaDTO() {
    }

    public PessoaDTO(String username, String email, String nome, String contactoTelefonico) {
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
