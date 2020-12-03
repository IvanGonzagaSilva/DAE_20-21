package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@NamedQueries({
    @NamedQuery(
        name = "getAllParametrosCalculo",
        query = "SELECT p FROM ParametrosCalculo p ORDER BY p.id" // JPQL
    )
})

@Entity
public class ParametrosCalculo implements Serializable {
    @Id
    private int id;

    private boolean contraventamentoTotal;

    private int numeroContraventamentosLaterais;

    private boolean contribuicaoChapaRevestimento;

    private int numeroFixacoes;

    private int inerciaChapaRevestimento;

    private String verificacaoDeformacao;

    private int limiteDeformacao;

    public ParametrosCalculo() {

    }

    public ParametrosCalculo(int id, boolean contraventamentoTotal, String verificacaoDeformacao, int limiteDeformacao) {
        this.id = id;
        this.contraventamentoTotal = contraventamentoTotal;
        this.verificacaoDeformacao = verificacaoDeformacao;
        this.limiteDeformacao = limiteDeformacao;
    }

    public ParametrosCalculo(int id, boolean contraventamentoTotal, int numeroContraventamentosLaterais, boolean contribuicaoChapaRevestimento, String verificacaoDeformacao, int limiteDeformacao) {
        this.id = id;
        this.contraventamentoTotal = contraventamentoTotal;
        this.numeroContraventamentosLaterais = numeroContraventamentosLaterais;
        this.contribuicaoChapaRevestimento = contribuicaoChapaRevestimento;
        this.verificacaoDeformacao = verificacaoDeformacao;
        this.limiteDeformacao = limiteDeformacao;
    }

    public ParametrosCalculo(int id, boolean contraventamentoTotal, int numeroContraventamentosLaterais, boolean contribuicaoChapaRevestimento, int numeroFixacoes, int inerciaChapaRevestimento, String verificacaoDeformacao, int limiteDeformacao) {
        this.id = id;
        this.contraventamentoTotal = contraventamentoTotal;
        this.numeroContraventamentosLaterais = numeroContraventamentosLaterais;
        this.contribuicaoChapaRevestimento = contribuicaoChapaRevestimento;
        this.numeroFixacoes = numeroFixacoes;
        this.inerciaChapaRevestimento = inerciaChapaRevestimento;
        this.verificacaoDeformacao = verificacaoDeformacao;
        this.limiteDeformacao = limiteDeformacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isContraventamentoTotal() {
        return contraventamentoTotal;
    }

    public void setContraventamentoTotal(boolean contraventamentoTotal) {
        this.contraventamentoTotal = contraventamentoTotal;
    }

    public int getNumeroContraventamentosLaterais() {
        return numeroContraventamentosLaterais;
    }

    public void setNumeroContraventamentosLaterais(int numeroContraventamentosLaterais) {
        this.numeroContraventamentosLaterais = numeroContraventamentosLaterais;
    }

    public boolean isContribuicaoChapaRevestimento() {
        return contribuicaoChapaRevestimento;
    }

    public void setContribuicaoChapaRevestimento(boolean contribuicaoChapaRevestimento) {
        this.contribuicaoChapaRevestimento = contribuicaoChapaRevestimento;
    }

    public int getNumeroFixacoes() {
        return numeroFixacoes;
    }

    public void setNumeroFixacoes(int numeroFixacoes) {
        this.numeroFixacoes = numeroFixacoes;
    }

    public int getInerciaChapaRevestimento() {
        return inerciaChapaRevestimento;
    }

    public void setInerciaChapaRevestimento(int inerciaChapaRevestimento) {
        this.inerciaChapaRevestimento = inerciaChapaRevestimento;
    }

    public String getVerificacaoDeformacao() {
        return verificacaoDeformacao;
    }

    public void setVerificacaoDeformacao(String verificacaoDeformacao) {
        this.verificacaoDeformacao = verificacaoDeformacao;
    }

    public int getLimiteDeformacao() {
        return limiteDeformacao;
    }

    public void setLimiteDeformacao(int limiteDeformacao) {
        this.limiteDeformacao = limiteDeformacao;
    }
}
