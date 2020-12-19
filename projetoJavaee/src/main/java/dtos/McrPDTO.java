package dtos;

import java.io.Serializable;

public class McrPDTO implements Serializable {

  private Double L;
  private Double mcr_pValue;

  public McrPDTO() {
  }

  public McrPDTO(Double l, Double mcr_pValue) {
    L = l;
    this.mcr_pValue = mcr_pValue;
  }

  public Double getL() {
    return L;
  }

  public void setL(Double l) {
    L = l;
  }

  public Double getMcr_pValue() {
    return mcr_pValue;
  }

  public void setMcr_pValue(Double mcr_pValue) {
    this.mcr_pValue = mcr_pValue;
  }
}
