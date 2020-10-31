package dtos;


import javax.persistence.ManyToOne;
import java.io.Serializable;

public class ProjetoDTO implements Serializable {

    private int id;
    private String nome;
    private ClienteDTO cliente;

    public ProjetoDTO() {
    }

    public ProjetoDTO(int id, String nome, ClienteDTO cliente) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
