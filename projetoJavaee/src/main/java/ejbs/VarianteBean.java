package ejbs;

import entities.Produto;
import entities.Variante;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "VarianteEJB")
public class VarianteBean {

    @PersistenceContext
    EntityManager em;

    public void create(int codigo, String nomeProduto, String name, double weff_p, double weff_n, double ar, double sigmaC){
        Produto produto = em.find(Produto.class, nomeProduto);
        Variante p = new Variante(codigo, produto, name, weff_p, weff_n, ar, sigmaC);
        em.persist(p);
    }

    public Variante find(int id) throws MyEntityNotFoundException {
      Variante variante = em.find(Variante.class, id);

      if (variante == null) {
        throw new MyEntityNotFoundException("Variante not found.");
      }

      return variante;
    }

    public Variante getVariante(int codigo){
        return em.find(Variante.class, codigo);
    }


}
