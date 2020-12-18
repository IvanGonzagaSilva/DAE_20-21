package entities;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
public class PessoaDeContacto extends Pessoa {

    public PessoaDeContacto() {
        super();
    }

    public PessoaDeContacto(String username, @Email String email, String nome, String contactoTelefonico, String password) {
        super(username, email, nome, contactoTelefonico, password);
    }
}
