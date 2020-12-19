package ejbs;

import entities.Ficheiro;
import entities.Projeto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityNotFoundException;
import exceptions.MyIllegalArgumentException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

@Stateless(name = "DocumentEJB")
public class FicheiroBean {

    @PersistenceContext
    EntityManager em;

    @EJB
    ProjetoBean projetoBean;

    public Ficheiro create(String filepath, String filename, int idProjeto) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {


        try {
            Ficheiro ficheiro = new Ficheiro(filepath, filename);
            Projeto projeto = projetoBean.find(idProjeto);

            if (projeto == null) {
                throw new MyEntityNotFoundException();
            }

            em.persist(ficheiro);

            projeto.addFicheiro(ficheiro);

            return ficheiro;

        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }

    }

    public Ficheiro findficheiro(int id) {

        return em.find(Ficheiro.class, id);
    }


}
