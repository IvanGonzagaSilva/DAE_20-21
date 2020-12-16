package dtos;

public class AplicacaoCoberturaDTO extends AplicacaoDTO {
    private int angulo;
    private int sobrecarga;
    private int neve;

    public AplicacaoCoberturaDTO() {
    }

    public AplicacaoCoberturaDTO(int id, int cargaPermanente, int ventoPressao, int ventoSucao, int angulo, int sobrecarga, int neve) {
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
