package entities;

import javax.persistence.*;
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

    private int numVaos;

    private int comprimentoVao;

    private int espacamentoVigas;

    //TODO provavelmente será necessário alterar os parametros de cálculo
    private String parametrosCalculo;

    @ManyToOne
    private Material material;

    public Estrutura() {
    }

    public Estrutura(int id, String nome, int numVaos, int comprimentoVao, int espacamentoVigas, String parametrosCalculo, Material material) {
        this.id = id;
        this.nome = nome;
        this.numVaos = numVaos;
        this.comprimentoVao = comprimentoVao;
        this.espacamentoVigas = espacamentoVigas;
        this.parametrosCalculo = parametrosCalculo;
        this.material = material;
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
