package ejbs;

import entities.Familia;
import entities.Geometria;
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
public class GeometriaBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int id, int numeroVaos, int comprimentoVao, int espacamentoVigas)
            throws MyEntityExistsException, MyConstraintViolationException {

        Geometria geometria = em.find(Geometria.class, id);

        if (geometria != null)
            throw new MyEntityExistsException("Geometria with id: " + id + " already exists");

        try {
            geometria = new Geometria(id, numeroVaos, comprimentoVao, espacamentoVigas);
            em.persist(numeroVaos);
        }
        catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Geometria update(int id, int numeroVaos, int comprimentoVao, int espacamentoVigas) {

        Geometria geometria = em.find(Geometria.class, id);

        if (geometria == null)
            throw new EntityNotFoundException("Geometria not found.");

        em.lock(geometria, LockModeType.OPTIMISTIC);
        geometria.setNumeroVaos(numeroVaos);
        geometria.setComprimentoVao(comprimentoVao);
        geometria.setEspacamentoVigas(espacamentoVigas);
        em.merge(geometria);

        return geometria;
    }

    public Geometria findGeometria(int id)
            throws MyEntityNotFoundException {

        Geometria geometria = em.find(Geometria.class, id);

        if (geometria == null)
            throw new MyEntityNotFoundException("Geometria not found.");

        return geometria;
    }

    public void addFamiliaToGeometria(int idGeometria, int idFamilia)
            throws MyEntityNotFoundException {

        Geometria geometria = em.find(Geometria.class, idGeometria);
        Familia familia = em.find(Familia.class, idFamilia);

        if (geometria == null || familia == null)
            throw new MyEntityNotFoundException("Geometria or Familia not found.");

        geometria.addFamilia(familia);
    }

    public void removeFamiliaFromGeometria(int idGeometria, int idFamilia)
            throws MyEntityNotFoundException {

        Geometria geometria = em.find(Geometria.class, idGeometria);
        Familia familia = em.find(Familia.class, idFamilia);

        if (geometria == null || familia == null)
            throw new MyEntityNotFoundException("Geometria or Familia not found.");

        geometria.removeFamilia(familia);
    }

    public List<Geometria> getAllGeometrias() {
        return (List<Geometria>) em.createNamedQuery("getAllGeometrias").getResultList();
    }
}
