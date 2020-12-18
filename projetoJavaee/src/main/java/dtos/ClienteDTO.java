package dtos;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private String nome;
    private PessoaDeContactoDTO pc;
    private String morada;
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, PessoaDeContactoDTO pc, String morada, String email) {
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

    public PessoaDeContactoDTO getPc() {
        return pc;
    }

    public void setPc(PessoaDeContactoDTO pc) {
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
