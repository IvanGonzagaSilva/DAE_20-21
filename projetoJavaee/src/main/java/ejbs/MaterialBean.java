package ejbs;

import entities.Material;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class MaterialBean {

    @PersistenceContext
    private EntityManager em;

    public void create(Integer id, String nome)
            throws MyEntityExistsException, MyConstraintViolationException {

        Material material = em.find(Material.class, id);

        if (material != null)
            throw new MyEntityExistsException("Material with id: " + id + " already exists");

        try {
            material = new Material(id, nome);
            em.persist(material);
        }
        catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Material update(Integer id, String nome) {

        Material material = em.find(Material.class, id);

        if (material == null)
            throw new EntityNotFoundException("Material not found.");

        em.lock(material, LockModeType.OPTIMISTIC);
        material.setNome(nome);
        em.merge(material);

        return material;
    }

    public Material findMaterial(Integer id)
            throws MyEntityNotFoundException {

        Material material = em.find(Material.class, id);

        if (material == null)
            throw new MyEntityNotFoundException("Material not found.");

        return material;
    }

    public List<Material> getAllMateriais() {
        return (List<Material>) em.createNamedQuery("getAllMateriais").getResultList();
    }
}
