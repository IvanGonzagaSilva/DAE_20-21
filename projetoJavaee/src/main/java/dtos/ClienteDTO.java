package dtos;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private String nome;
    private PessoaDeContactoDTO pc;
    private String morada;
    private String email;
    private String username;
    private String password;
    private int id;

    public ClienteDTO() {
    }

  public ClienteDTO(String nome, PessoaDeContactoDTO pc, String morada, String email, String username, String password, int id) {
    this.nome = nome;
    this.pc = pc;
    this.morada = morada;
    this.email = email;
    this.username = username;
    this.password = password;
    this.id = id;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
