package dtos;


import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProjetoDTO implements Serializable {

    private int id;
    private String nome;
    private String usernameProjetista;
    private ClienteDTO cliente;
    private Set<EstruturaDTO> estruturas;

    public ProjetoDTO() {
        estruturas = new LinkedHashSet<>();
    }

    public ProjetoDTO(int id, String nome, ClienteDTO cliente, Set<EstruturaDTO> estruturas, String usernameProjetista) {
        this();
        this.id = id;
        this.nome = nome;
        this.usernameProjetista = usernameProjetista;
        this.cliente = cliente;
        this.estruturas = estruturas;
    }

    public ProjetoDTO(int id, String nome, ClienteDTO cliente, Set<EstruturaDTO> estruturas) {
        this();
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

    public String getUsernameProjetista() {
        return usernameProjetista;
    }

    public void setUsernameProjetista(String usernameProjetista) {
        this.usernameProjetista = usernameProjetista;
    }
}
