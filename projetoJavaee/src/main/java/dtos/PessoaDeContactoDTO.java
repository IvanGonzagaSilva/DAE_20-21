package dtos;

public class PessoaDeContactoDTO extends PessoaDTO{

    public PessoaDeContactoDTO() {
    }

    public PessoaDeContactoDTO(String username, String email, String nome, String contactoTelefonico) {
        super(username, email, nome, contactoTelefonico);
    }
}
