package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(
        name = "getAllAplicacoesCoberturas",
        query = "SELECT ac FROM AplicacaoCobertura ac ORDER BY ac.id" // JPQL
    )
})

@Entity
public class AplicacaoCobertura extends Aplicacao {

    private int angulo;

    private int sobrecarga;

    private int neve;

    public AplicacaoCobertura() {

    }

    public AplicacaoCobertura(int id, int angulo, int cargaPermanente, int sobrecarga, int neve, int ventoPressao, int ventoSucao) {
        super(id, cargaPermanente, ventoPressao, ventoSucao);
        this.angulo = angulo;
        this.sobrecarga = sobrecarga;
        this.neve = neve;
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getSobrecarga() {
        return sobrecarga;
    }

    public void setSobrecarga(int sobrecarga) {
        this.sobrecarga = sobrecarga;
    }

    public int getNeve() {
        return neve;
    }

    public void setNeve(int neve) {
        this.neve = neve;
    }
}
