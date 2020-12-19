package ejbs;

import entities.Cliente;
import entities.PessoaDeContacto;
import entities.Projeto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless(name = "ClienteEJB")
public class ClienteBean {

    @PersistenceContext
    EntityManager em;

    public ClienteBean() {
    }

    public Cliente create(String nome, String usernamePc, String morada, String email, String username, String password)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException
    {
        Cliente cliente = em.find(Cliente.class, email);

        if(cliente != null){
            throw new MyEntityExistsException();
        }

        PessoaDeContacto pc = em.find(PessoaDeContacto.class, usernamePc);

        if(pc == null){
            throw new MyEntityNotFoundException();
        }

        try {
            cliente = new Cliente(nome, pc, morada, email, username, password);

            em.persist(cliente);

            return cliente;
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }

    }

    public Cliente find(String email) throws MyEntityNotFoundException {
        Cliente cliente = em.find(Cliente.class, email);

        if(cliente ==null){
            throw new MyEntityNotFoundException();
        }

        return cliente;
    }

    public List<Projeto> getAllProjetos(int idClient) {
      return em.createNamedQuery("getAllClientProjetos", Projeto.class).setParameter("idCliente", idClient).getResultList();
    }
}
