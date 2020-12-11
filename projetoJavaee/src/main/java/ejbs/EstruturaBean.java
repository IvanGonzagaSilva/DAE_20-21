package ejbs;

import dtos.EstruturaDTO;
import entities.Estrutura;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashSet;
import java.util.Set;

@Stateless(name = "EstruturaEJB")
public class EstruturaBean {

    @PersistenceContext
    EntityManager em;

    public Estrutura create(){
        Estrutura estrutura = new Estrutura();
        em.persist(estrutura);
        return estrutura;
    }

    public Estrutura find(String id) throws MyEntityNotFoundException {
        Estrutura estrutura = em.find(Estrutura.class, id);

        if(estrutura == null){
            throw new MyEntityNotFoundException();
        }

        return estrutura;
    }


}
