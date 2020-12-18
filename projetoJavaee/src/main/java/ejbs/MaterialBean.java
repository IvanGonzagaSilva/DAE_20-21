package ejbs;

import entities.Material;
import entities.PessoaDeContacto;
import entities.Projeto;
import entities.TipoDeMaterial;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "MaterialEJB")
public class MaterialBean {

    @PersistenceContext
    EntityManager em;

    public MaterialBean() {
    }

    public Material create(TipoDeMaterial tipoDeMaterial){
        Material material = new Material(tipoDeMaterial);

        em.persist(material);

        return material;
    }

    public List<Material> getAllMateriais() {
        return (List<Material>) em.createNamedQuery("getAllMateriais").getResultList();
    }
    
}
