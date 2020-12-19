package dtos;

public class ClienteDTO extends PessoaDTO {


    private PessoaDeContactoDTO pc;
    private String morada;

    public ClienteDTO() {
    }


    public ClienteDTO(String username, String email, String nome, String contactoTelefonico, String password, PessoaDeContactoDTO pc, String morada) {
        super(username, email, nome, contactoTelefonico, password);
        this.pc = pc;
        this.morada = morada;
    }

    public PessoaDeContactoDTO getPc() {
        return pc;
    }

    public void setPc(PessoaDeContactoDTO pc) {
        this.pc = pc;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}
