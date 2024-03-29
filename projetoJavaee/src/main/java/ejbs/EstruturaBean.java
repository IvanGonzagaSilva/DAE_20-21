package ejbs;

import dtos.EstruturaDTO;
import entities.Estrutura;
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

    public Estrutura find(String id){
        return em.find(Estrutura.class, id);
    }


}
