package ejbs;

import entities.Aplicacao;
import entities.AplicacaoCobertura;
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
public class AplicacaoBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int cargaPermanente, int sobrecarga, int neve, int ventoPressao, int ventoSucao, String tipo)
            throws MyEntityExistsException, MyConstraintViolationException {

        try {
            Aplicacao aplicacao = new Aplicacao(cargaPermanente, ventoPressao, ventoSucao, tipo);
            em.persist(aplicacao);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Aplicacao update(int id, int cargaPermanente, int ventoPressao, int ventoSucao) {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao == null)
            throw new EntityNotFoundException("Aplicação not found.");

        em.lock(aplicacao, LockModeType.OPTIMISTIC);
        aplicacao.setCargaPermanente(cargaPermanente);
        aplicacao.setVentoPressao(ventoPressao);
        aplicacao.setVentoSucao(ventoSucao);
        em.merge(aplicacao);

        return aplicacao;
    }

    public Aplicacao findAplicacao(Integer id)
            throws MyEntityNotFoundException {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao == null)
            throw new MyEntityNotFoundException("Aplicação not found.");

        return aplicacao;
    }

    public List<Aplicacao> getAllAplicacoes() {
        return (List<Aplicacao>) em.createNamedQuery("getAllAplicacoes").getResultList();
    }
}
