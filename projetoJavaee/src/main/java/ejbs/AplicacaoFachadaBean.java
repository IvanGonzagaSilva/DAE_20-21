package ejbs;

import entities.AplicacaoFachada;
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
public class AplicacaoFachadaBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int cargaPermanente, int ventoPressao, int ventoSucao)
            throws MyEntityExistsException, MyConstraintViolationException {


        try {
            AplicacaoFachada aplicacaoFachada = new AplicacaoFachada(cargaPermanente, ventoPressao, ventoSucao);

            em.persist(aplicacaoFachada);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public AplicacaoFachada update(int id, int cargaPermanente, int ventoPressao, int ventoSucao) {

        AplicacaoFachada aplicacaoFachada = em.find(AplicacaoFachada.class, id);

        if (aplicacaoFachada == null)
            throw new EntityNotFoundException("Aplicação fachada not found.");

        em.lock(aplicacaoFachada, LockModeType.OPTIMISTIC);
        aplicacaoFachada.setCargaPermanente(cargaPermanente);
        aplicacaoFachada.setVentoPressao(ventoPressao);
        aplicacaoFachada.setVentoSucao(ventoSucao);
        em.merge(aplicacaoFachada);

        return aplicacaoFachada;
    }

    public AplicacaoFachada findAplicacaoFachada(Integer id)
            throws MyEntityNotFoundException {

        AplicacaoFachada aplicacaoFachada = em.find(AplicacaoFachada.class, id);

        if (aplicacaoFachada == null)
            throw new MyEntityNotFoundException("Aplicação fachada not found.");

        return aplicacaoFachada;
    }

    public List<AplicacaoFachada> getAllAplicacoesFachadas() {
        return (List<AplicacaoFachada>) em.createNamedQuery("getAllAplicacoesFachadas").getResultList();
    }
}
