package ejbs;

import entities.Cliente;
import entities.PessoaDeContacto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "PessoaDeContactoEJB")
public class PessoaDeContactoBean {

    @PersistenceContext
    EntityManager em;

    public PessoaDeContactoBean() {
    }

    public PessoaDeContacto create(String username, String email, String name, String contactoTelefonico){
        PessoaDeContacto pcexists = em.find(PessoaDeContacto.class, username);

        if(pcexists != null){
            return pcexists;
        }

        PessoaDeContacto pc = new PessoaDeContacto(username, email, name, contactoTelefonico);

        em.persist(pc);

        return pc;

    }


}
