package ejbs;

import entities.Projetista;
import entities.Projeto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import org.eclipse.persistence.sessions.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless(name = "ProjetistaEJB")
public class ProjetistaBean {

    @PersistenceContext
    EntityManager em;

    public ProjetistaBean() {
    }

    public Projetista create(String username, String email, String nome, String contactoTelefonico, String password) throws MyEntityExistsException, MyConstraintViolationException {
        Projetista projetistaExists = em.find(Projetista.class, username);

        if(projetistaExists != null){
            throw new MyEntityExistsException();
        }

        try {
            Projetista projetista = new Projetista(username, email, nome, contactoTelefonico, password);

            em.persist(projetista);

            return projetista;
        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public void enrollInProject(String username, int projetoid){
        Projetista projetista = em.find(Projetista.class, username);

        if(projetista == null){
            return; // trocar por throws...
        }

        Projeto projeto = em.find(Projeto.class, projetoid);

        if(projeto == null){
            return; //throws myentitynotfound...
        }

        if(!projetista.getProjetos().contains(projeto)){
            projetista.addProjeto(projeto);
        }

    }

    public void unrollFromProject(String username, int projetoid){
        Projetista projetista = em.find(Projetista.class, username);

        if(projetista == null){
            return; // trocar por throws...
        }

        Projeto projeto = em.find(Projeto.class, projetoid);

        if(projeto == null){
            return; //throws myentitynotfound...
        }

        if(projetista.getProjetos().contains(projeto)){
            projetista.removeProjeto(projeto);
        }
    }

    public Projetista find(String usernameProjetista) {
        return em.find(Projetista.class, usernameProjetista);
    }
}
