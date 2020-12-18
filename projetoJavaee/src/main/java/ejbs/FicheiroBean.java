package ejbs;

import entities.Ficheiro;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Stateless(name = "DocumentEJB")
public class FicheiroBean {

    @PersistenceContext
    EntityManager em;

    @EJB
    ProjetoBean projetoBean;

    public Ficheiro create(String filepath, String filename, int idProjeto) {
        Ficheiro ficheiro = new Ficheiro(filepath, filename);
        Projeto projeto = projetoBean.find(idProjeto);

        if(filepath.isEmpty() ||filename.isEmpty() || projeto == null){
            return null;
        }

        projeto.addFicheiro(ficheiro);

        return ficheiro;
    }

    public Ficheiro findficheiro(int id){

        return em.find(Ficheiro.class, id);
    }



}
