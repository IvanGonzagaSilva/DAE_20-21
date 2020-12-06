package ejbs;

import dtos.ProjetoDTO;
import entities.Cliente;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "ProjetoEJB")
public class ProjetoBean {

    @PersistenceContext
    EntityManager em;
    @EJB
    ClienteBean clienteBean;
    @EJB
    EstruturaBean estruturaBean;

    public ProjetoBean() {
    }

    public Projeto create(String nome, String emailCliente){

        Cliente cliente = em.find(Cliente.class, emailCliente);

        if(cliente == null){
            return null; //TODO alterar para throws ....
        }

        Projeto projeto = new Projeto(nome, cliente);

        cliente.addProjeto(projeto);

        em.persist(projeto);

        return projeto;
    }

    public List<Projeto> getAllProjetos() {
        return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
    }

    public Projeto find(int idProjeto){
        return em.find(Projeto.class, idProjeto);
    }

    public void delete(int idprojeto){
        Projeto projeto = find(idprojeto);

        if(projeto != null){
            em.remove(projeto);
        }
    }


//    public void update(ProjetoDTO projetoDTO) {
//        Projeto projeto = find(projetoDTO.getId());
//
//        if (projeto!=null){
//            projeto.setCliente(clienteBean.find(projetoDTO.getCliente().getEmail()));
//            projeto.setNome(projetoDTO.getNome());
//        }
//
//    }
}
