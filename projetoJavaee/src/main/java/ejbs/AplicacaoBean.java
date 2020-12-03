package ejbs;

import entities.*;
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

    public void createFachada(int id, int cargaPermanente, int ventoPressao, int ventoSucao)
            throws MyEntityExistsException, MyConstraintViolationException {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao != null)
            throw new MyEntityExistsException("Aplicação with id: " + id + " already exists");

        try {
            aplicacao = new Aplicacao(id, "Fachada", cargaPermanente, ventoPressao, ventoSucao);
            em.persist(aplicacao);
        }
        catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void createCobertura(int id, int angulo, int cargaPermanente, int sobrecarga, int neve, int ventoPressao, int ventoSucao)
            throws MyEntityExistsException, MyConstraintViolationException {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao != null)
            throw new MyEntityExistsException("Aplicação with id: " + id + " already exists");

        try {
            aplicacao = new Aplicacao(id, "Cobertura", angulo, cargaPermanente, sobrecarga, neve, ventoPressao, ventoSucao);
            em.persist(aplicacao);
        }
        catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void createGeral(int id, int angulo, int cargaPermanente, int sobrecarga, int categoriaSobrecarga, int neve, int ventoPressao, int ventoSucao)
            throws MyEntityExistsException, MyConstraintViolationException {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao != null)
            throw new MyEntityExistsException("Aplicação with id: " + id + " already exists");

        try {
            aplicacao = new Aplicacao(id, "Geral", angulo, cargaPermanente, sobrecarga, categoriaSobrecarga, neve, ventoPressao, ventoSucao);
            em.persist(aplicacao);
        }
        catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Aplicacao updateFachada(int id, String tipo, int cargaPermanente, int ventoPressao, int ventoSucao) {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao == null)
            throw new EntityNotFoundException("Aplicacao not found.");

        em.lock(aplicacao, LockModeType.OPTIMISTIC);
        aplicacao.setTipo(tipo);
        aplicacao.setCargaPermanente(cargaPermanente);
        aplicacao.setVentoPressao(ventoPressao);
        aplicacao.setVentoSucao(ventoSucao);
        em.merge(aplicacao);

        return aplicacao;
    }

    public Aplicacao updateCobertura(int id, String tipo, int angulo, int cargaPermanente, int sobrecarga, int neve, int ventoPressao, int ventoSucao) {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao == null)
            throw new EntityNotFoundException("Aplicacao not found.");

        em.lock(aplicacao, LockModeType.OPTIMISTIC);
        aplicacao.setAngulo(angulo);
        aplicacao.setTipo(tipo);
        aplicacao.setCargaPermanente(cargaPermanente);
        aplicacao.setSobrecarga(sobrecarga);
        aplicacao.setNeve(neve);
        aplicacao.setVentoPressao(ventoPressao);
        aplicacao.setVentoSucao(ventoSucao);
        em.merge(aplicacao);

        return aplicacao;
    }

    public Aplicacao updateGeral(int id, String tipo, int angulo, int cargaPermanente, int sobrecarga, int categoriaSobrecarga, int neve, int ventoPressao, int ventoSucao) {

        Aplicacao aplicacao = em.find(Aplicacao.class, id);

        if (aplicacao == null)
            throw new EntityNotFoundException("Aplicacao not found.");

        em.lock(aplicacao, LockModeType.OPTIMISTIC);
        aplicacao.setAngulo(angulo);
        aplicacao.setTipo(tipo);
        aplicacao.setCargaPermanente(cargaPermanente);
        aplicacao.setSobrecarga(sobrecarga);
        aplicacao.setCategoriaSobrecarga(categoriaSobrecarga);
        aplicacao.setNeve(neve);
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
