package ejbs;

import entities.Estrutura;
import entities.Geometria;
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
public class EstruturaBean {

    @PersistenceContext
    private EntityManager em;

    public void create(Integer id, String nome, Integer idGeometria, Integer idMaterial)
            throws MyEntityExistsException, MyConstraintViolationException {

        Geometria geometria = em.find(Geometria.class, idGeometria);

        if (geometria == null)
            throw new EntityNotFoundException("Geometria not found.");

        Material material = em.find(Material.class, idMaterial);

        if (material == null)
            throw new EntityNotFoundException("Material not found.");

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura != null)
            throw new MyEntityExistsException("Estrutura with id: " + id + " already exists");

        try {
            estrutura = new Estrutura(id, nome, geometria, material);
            em.persist(estrutura);
        }
        catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Estrutura update(Integer id, String nome, Integer idGeometria, Integer idMaterial) {

        Geometria geometria = em.find(Geometria.class, idGeometria);

        if (geometria == null)
            throw new EntityNotFoundException("Geometria not found.");

        Material material = em.find(Material.class, idMaterial);

        if (material == null)
            throw new EntityNotFoundException("Material not found.");

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura == null)
            throw new EntityNotFoundException("Estrutura not found.");

        em.lock(estrutura, LockModeType.OPTIMISTIC);
        estrutura.setNome(nome);
        estrutura.setGeometria(geometria);
        estrutura.setMaterial(material);
        em.merge(estrutura);

        return estrutura;
    }

    public Estrutura findEstrutura(Integer id)
            throws MyEntityNotFoundException {

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura == null)
            throw new MyEntityNotFoundException("Estrutura not found.");

        return estrutura;
    }

    public List<Estrutura> getAllEstruturas() {
        return (List<Estrutura>) em.createNamedQuery("getAllEstruturas").getResultList();
    }
}
