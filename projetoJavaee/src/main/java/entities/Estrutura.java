package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//Estrutura de um determinado projeto
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllEstruturas",
                query = "SELECT e FROM Estrutura e ORDER BY e.nome" // JPQL
        )
})

public class Estrutura implements Serializable {
    @Id
    private int id;

    private String nome;

    private String tipoMaterial;

    private int numVaos;

    private int comprimentoVao;

    private int espacamentoVigas;

    //TODO provavelmente será necessário alterar os parametros de cálculo
    private String parametrosCalculo;

    //TODO descomentar após implementar os materiais ou produtos
    //Lista de materiais / produtos
    //@OneToMany
    //private List<Material> materiais;

    public Estrutura() {
        //this.materiais = new LinkedList<Material>();
    }

    public Estrutura(int id, @NotNull String nome, @NotNull String tipoMaterial, @NotNull int numVaos, @NotNull int comprimentoVao, @NotNull int espacamentoVigas, @NotNull String parametrosCalculo) {
        this.id = id;
        this.nome = nome;
        this.tipoMaterial = tipoMaterial;
        this.numVaos = numVaos;
        this.comprimentoVao = comprimentoVao;
        this.espacamentoVigas = espacamentoVigas;
        this.parametrosCalculo = parametrosCalculo;
        //this.materiais = new LinkedList<Material>();
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

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public int getNumVaos() {
        return numVaos;
    }

    public void setNumVaos(int numVaos) {
        this.numVaos = numVaos;
    }

    public int getComprimentoVao() {
        return comprimentoVao;
    }

    public void setComprimentoVao(int comprimentoVao) {
        this.comprimentoVao = comprimentoVao;
    }

    public int getEspacamentoVigas() {
        return espacamentoVigas;
    }

    public void setEspacamentoVigas(int espacamentoVigas) {
        this.espacamentoVigas = espacamentoVigas;
    }

    public String getParametrosCalculo() {
        return parametrosCalculo;
    }

    public void setParametrosCalculo(String parametrosCalculo) {
        this.parametrosCalculo = parametrosCalculo;
    }
}
