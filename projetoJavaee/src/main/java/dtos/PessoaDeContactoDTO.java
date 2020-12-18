package dtos;

public class PessoaDeContactoDTO extends PessoaDTO{

    public PessoaDeContactoDTO() {
    }

    public PessoaDeContactoDTO(String username, String email, String nome, String contactoTelefonico, String password) {
        super(username, email, nome, contactoTelefonico, password);
    }
}
