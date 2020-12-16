package dtos;

public class AplicacaoGeralDTO extends AplicacaoDTO {

    private int angulo;
    private int sobrecarga;
    private int categoriaSobrecarga;
    private int neve;

    public AplicacaoGeralDTO() {
    }

    public AplicacaoGeralDTO(int id, int cargaPermanente, int ventoPressao, int ventoSucao, int angulo, int sobrecarga, int categoriaSobrecarga, int neve) {
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
