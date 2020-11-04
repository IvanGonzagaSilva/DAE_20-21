package ejbs;

import entities.Cliente;
import entities.Projeto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "ProjetoEJB")
public class ProjetoBean {

    @PersistenceContext
    EntityManager em;

    public ProjetoBean() {
    }

    public Projeto create(int id, String nome, String emailCliente){
        Projeto projetoExists = em.find(Projeto.class, id);

        if(projetoExists != null){
            return projetoExists; //TODO alterar para throws...
        }

        Cliente cliente = em.find(Cliente.class, emailCliente);

        if(cliente == null){
            return null; //TODO alterar para throws ....
        }

        Projeto projeto = new Projeto(id, nome, cliente);

        cliente.addProjeto(projeto);

        em.persist(projeto);

        return projeto;
    }

    public List<Projeto> getAllProjetos() {
        return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
    }


}
