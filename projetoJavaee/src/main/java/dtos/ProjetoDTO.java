package dtos;


import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProjetoDTO implements Serializable {

    private int id;
    private String nome;
    private ClienteDTO cliente;
    private Set<EstruturaDTO> estruturas;

    public ProjetoDTO() {
        estruturas = new LinkedHashSet<>();
    }

    public ProjetoDTO(int id, String nome, ClienteDTO cliente, Set<EstruturaDTO> estruturas) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
        this.estruturas = estruturas;
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

    public Set<EstruturaDTO> getEstruturas() {
        return estruturas;
    }

    public void setEstruturas(Set<EstruturaDTO> estruturas) {
        this.estruturas = estruturas;
    }
}
