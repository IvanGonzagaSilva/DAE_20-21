package ejbs;

import entities.Produto;
import entities.Variante;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "VarianteEJB")
public class VarianteBean {

    @PersistenceContext
    EntityManager em;

    public Variante create(String nomeProduto, String name, double weff_p, double weff_n, double ar, double sigmaC) {
        Produto produto = em.find(Produto.class, nomeProduto);
        Variante p = new Variante(produto, name, weff_p, weff_n, ar, sigmaC);

        em.persist(p);
        return p;
    }

    public Variante getVariante(int codigo) {
        return em.find(Variante.class, codigo);
    }


}
