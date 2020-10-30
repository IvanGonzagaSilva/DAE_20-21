package ejbs;

import entities.Projetista;
import entities.Projeto;
import org.eclipse.persistence.sessions.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProjetistaBean {

    @PersistenceContext
    EntityManager em;

    public ProjetistaBean() {
    }

    public Projetista create(String username, String email, String nome, String contactoTelefonico){
        Projetista projetistaExists = em.find(Projetista.class, username);

        if(projetistaExists != null){
            return projetistaExists;
        }

        Projetista projetista = new Projetista(username, email, nome, contactoTelefonico);

        em.persist(projetista);

        return projetista;
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
}
