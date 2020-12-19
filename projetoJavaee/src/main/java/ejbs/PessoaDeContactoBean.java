package ejbs;

import entities.PessoaDeContacto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless(name = "PessoaDeContactoEJB")
public class PessoaDeContactoBean {

    @PersistenceContext
    EntityManager em;

    public PessoaDeContactoBean() {
    }

    public PessoaDeContacto create(String username, String email, String name, String contactoTelefonico, String password) throws MyEntityExistsException, MyConstraintViolationException {
        PessoaDeContacto pc = this.find(username);

        if (pc != null) {
            throw new MyEntityExistsException();
        }

        try {
            pc = new PessoaDeContacto(username, email, name, contactoTelefonico, password);

            em.persist(pc);

            return pc;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }


    }


    public PessoaDeContacto find(String username) {
        return em.find(PessoaDeContacto.class, username);
    }
}
