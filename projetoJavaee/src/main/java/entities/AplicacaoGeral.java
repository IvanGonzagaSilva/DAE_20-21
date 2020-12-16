package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
        @NamedQuery(
                name = "getAllAplicacoesGerais",
                query = "SELECT ag FROM AplicacaoGeral ag ORDER BY ag.id" // JPQL
        )
})

@Entity
public class AplicacaoGeral extends Aplicacao {

    private int angulo;

    private int sobrecarga;

    private int categoriaSobrecarga;

    private int neve;

    public AplicacaoGeral() {

    }

    public AplicacaoGeral(int id, int angulo, int cargaPermanente, int sobrecarga, int categoriaSobrecarga, int neve, int ventoPressao, int ventoSucao) {
        super(id, cargaPermanente, ventoPressao, ventoSucao);
        this.angulo = angulo;
        this.sobrecarga = sobrecarga;
        this.categoriaSobrecarga = categoriaSobrecarga;
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

    public int getCategoriaSobrecarga() {
        return categoriaSobrecarga;
    }

    public void setCategoriaSobrecarga(int categoriaSobrecarga) {
        this.categoriaSobrecarga = categoriaSobrecarga;
    }

    public int getNeve() {
        return neve;
    }

    public void setNeve(int neve) {
        this.neve = neve;
    }
}
