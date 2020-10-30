package ejbs;

import entities.Cliente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class clienteBean {

    @PersistenceContext
    EntityManager em;

    public clienteBean() {
    }

    public Cliente create(String username, String password){
        Cliente cliente = em.find(Cliente.class, username);

        if(cliente != null){
            return cliente;
        }

        Cliente cli = new Cliente(username, password);

        em.persist(cli);

        return cli;

    }
}
