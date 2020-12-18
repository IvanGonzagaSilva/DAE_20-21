package ejbs;

import entities.Aplicacao;
import entities.AplicacaoGeral;
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
public class AplicacaoGeralBean {

    @PersistenceContext
    private EntityManager em;

    public AplicacaoGeral create(int angulo, int cargaPermanente, int sobrecarga, int categoriaSobrecarga, int neve, int ventoPressao, int ventoSucao)
            throws MyEntityExistsException, MyConstraintViolationException {

        try {
            AplicacaoGeral aplicacaoGeral = new AplicacaoGeral(angulo, cargaPermanente, sobrecarga, categoriaSobrecarga, neve, ventoPressao, ventoSucao);
            em.persist(aplicacaoGeral);
            return aplicacaoGeral;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Aplicacao update(int id, int angulo, int cargaPermanente, int sobrecarga, int categoriaSobrecarga, int neve, int ventoPressao, int ventoSucao) {

        AplicacaoGeral aplicacaoGeral = em.find(AplicacaoGeral.class, id);

        if (aplicacaoGeral == null)
            throw new EntityNotFoundException("Aplicacao not found.");

        em.lock(aplicacaoGeral, LockModeType.OPTIMISTIC);
        aplicacaoGeral.setAngulo(angulo);
        aplicacaoGeral.setCargaPermanente(cargaPermanente);
        aplicacaoGeral.setSobrecarga(sobrecarga);
        aplicacaoGeral.setCategoriaSobrecarga(categoriaSobrecarga);
        aplicacaoGeral.setNeve(neve);
        aplicacaoGeral.setVentoPressao(ventoPressao);
        aplicacaoGeral.setVentoSucao(ventoSucao);
        em.merge(aplicacaoGeral);

        return aplicacaoGeral;
    }

    public AplicacaoGeral findAplicacao(Integer id)
            throws MyEntityNotFoundException {

        AplicacaoGeral aplicacaoGeral = em.find(AplicacaoGeral.class, id);

        if (aplicacaoGeral == null)
            throw new MyEntityNotFoundException("Aplicação geral not found.");

        return aplicacaoGeral;
    }

    public List<AplicacaoGeral> getAllAplicacoesGerais() {
        return (List<AplicacaoGeral>) em.createNamedQuery("getAllAplicacoesGerais").getResultList();
    }
}
