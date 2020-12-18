package dtos;

import entities.Produto;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashMap;

public class VarianteDTO implements Serializable {

    private static double G = 78.5;
    private int codigo;
    private String nome;
    private double weff_p;
    private double weff_n;
    private double ar;
    private double sigmaC;
    private String nomeProduto;
    private double pp;
    private LinkedHashMap<Double,Double> mcr_p;
    private LinkedHashMap<Double,Double> mcr_n;

    public VarianteDTO() {
        this.mcr_p = new LinkedHashMap<Double,Double>();
        this.mcr_n = new LinkedHashMap<Double,Double>();
    }

    public VarianteDTO(int codigo, String nome, double weff_p, double weff_n, double ar, double sigmaC, String nomeProduto ) {
        this.codigo = codigo;
        this.nome = nome;
        this.weff_p = weff_p;
        this.weff_n = weff_n;
        this.ar = ar;
        this.sigmaC = sigmaC;
        this.pp = G * ar * Math.pow(10, -6);
        this.nomeProduto = nomeProduto;

    }

    public VarianteDTO(int codigo, String nome, double weff_p, double weff_n, double ar, double sigmaC, double pp, String nomeProduto) {
        this.nomeProduto = nomeProduto;
        this.codigo = codigo;
        this.nome = nome;
        this.weff_p = weff_p;
        this.weff_n = weff_n;
        this.ar = ar;
        this.sigmaC = sigmaC;
        this.pp = pp;
    }

    public static double getG() {
        return G;
    }

    public static void setG(double g) {
        G = g;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getWeff_p() {
        return weff_p;
    }

    public void setWeff_p(double weff_p) {
        this.weff_p = weff_p;
    }

    public double getWeff_n() {
        return weff_n;
    }

    public void setWeff_n(double weff_n) {
        this.weff_n = weff_n;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    public double getSigmaC() {
        return sigmaC;
    }

    public void setSigmaC(double sigmaC) {
        this.sigmaC = sigmaC;
    }

    public double getPp() {
        return pp;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }

    public LinkedHashMap<Double, Double> getMcr_p() {
        return mcr_p;
    }

    public void setMcr_p(LinkedHashMap<Double, Double> mcr_p) {
        this.mcr_p = mcr_p;
    }

    public LinkedHashMap<Double, Double> getMcr_n() {
        return mcr_n;
    }

    public void setMcr_n(LinkedHashMap<Double, Double> mcr_n) {
        this.mcr_n = mcr_n;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
