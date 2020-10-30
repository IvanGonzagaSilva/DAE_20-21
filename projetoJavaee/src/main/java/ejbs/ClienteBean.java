package ejbs;

import entities.Cliente;
import entities.PessoaDeContacto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClienteBean {

    @PersistenceContext
    EntityManager em;

    public ClienteBean() {
    }

    public Cliente create(String nome, String usernamePc, String morada, String email){
        Cliente cliente = em.find(Cliente.class, email);

        if(cliente != null){
            return cliente;
        }

        PessoaDeContacto pc = em.find(PessoaDeContacto.class, usernamePc);

        Cliente cli = new Cliente(nome, pc, morada, email);

        em.persist(cli);

        return cli;

    }
}
