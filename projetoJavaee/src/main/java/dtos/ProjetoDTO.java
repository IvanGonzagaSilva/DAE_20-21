package dtos;


import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProjetoDTO implements Serializable {

    private int id;
    private String nome;
    private String usernameProjetista;
    private Set<EstruturaDTO> estruturas;
    private Set<ClienteDTO> clientes;

    public ProjetoDTO() {
        estruturas = new LinkedHashSet<>();
        clientes = new LinkedHashSet<>();
    }

    public ProjetoDTO(int id, String nome, Set<EstruturaDTO> estruturas, String usernameProjetista, Set<ClienteDTO> clientes) {
        this();
        this.id = id;
        this.nome = nome;
        this.usernameProjetista = usernameProjetista;
        this.estruturas = estruturas;
        this.clientes = clientes;
    }

  public Set<ClienteDTO> getClientes() {
    return clientes;
  }

  public void setClientes(Set<ClienteDTO> clientes) {
    this.clientes = clientes;
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


    public Set<EstruturaDTO> getEstruturas() {
        return estruturas;
    }

    public void setEstruturas(Set<EstruturaDTO> estruturas) {
        this.estruturas = estruturas;
    }

    public String getUsernameProjetista() {
        return usernameProjetista;
    }

    public void setUsernameProjetista(String usernameProjetista) {
        this.usernameProjetista = usernameProjetista;
    }
}
