package ejbs;

import entities.Familia;
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
public class FamiliaBean {
    @PersistenceContext
    private EntityManager em;

    public Familia create(String nome)
            throws MyEntityExistsException, MyConstraintViolationException {

        Familia familia = em.find(Familia.class, nome);

        if (familia != null)
            throw new MyEntityExistsException("Familia with nome: " + nome + " already exists");

        try {
            familia = new Familia(nome);
            em.persist(familia);
            return familia;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Familia update(int id, String nome) {

        Familia familia = em.find(Familia.class, id);

        if (familia == null)
            throw new EntityNotFoundException("Familia not found.");

        em.lock(familia, LockModeType.OPTIMISTIC);
        familia.setNome(nome);
        em.merge(familia);

        return familia;
    }

    public Familia findFamilia(Integer id) throws MyEntityNotFoundException {
        Familia familia = em.find(Familia.class, id);

        if (familia == null)
            throw new MyEntityNotFoundException("Familia not found.");

        return familia;
    }

    public List<Familia> getAllFamilias() {
        return (List<Familia>) em.createNamedQuery("getAllFamilias").getResultList();
    }
}
