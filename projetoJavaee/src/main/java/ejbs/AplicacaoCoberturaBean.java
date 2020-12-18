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
public class AplicacaoCoberturaBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int angulo, int cargaPermanente, int sobrecarga, int neve, int ventoPressao, int ventoSucao)
            throws MyEntityExistsException, MyConstraintViolationException {

        try {
            AplicacaoCobertura aplicacaoCobertura = new AplicacaoCobertura(angulo, cargaPermanente, sobrecarga, neve, ventoPressao, ventoSucao);

            em.persist(aplicacaoCobertura);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public AplicacaoCobertura update(int id, int angulo, int cargaPermanente, int sobrecarga, int neve, int ventoPressao, int ventoSucao) {

        AplicacaoCobertura aplicacaoCobertura = em.find(AplicacaoCobertura.class, id);

        if (aplicacaoCobertura == null)
            throw new EntityNotFoundException("Aplicação cobertura not found.");

        em.lock(aplicacaoCobertura, LockModeType.OPTIMISTIC);
        aplicacaoCobertura.setAngulo(angulo);
        aplicacaoCobertura.setCargaPermanente(cargaPermanente);
        aplicacaoCobertura.setSobrecarga(sobrecarga);
        aplicacaoCobertura.setNeve(neve);
        aplicacaoCobertura.setVentoPressao(ventoPressao);
        aplicacaoCobertura.setVentoSucao(ventoSucao);
        em.merge(aplicacaoCobertura);

        return aplicacaoCobertura;
    }

    public Aplicacao findAplicacaoCobertura(Integer id)
            throws MyEntityNotFoundException {

        AplicacaoCobertura aplicacaoCobertura = em.find(AplicacaoCobertura.class, id);

        if (aplicacaoCobertura == null)
            throw new MyEntityNotFoundException("Aplicação cobertura not found.");

        return aplicacaoCobertura;
    }

    public List<AplicacaoCobertura> getAllAplicacoesCoberturas() {
        return (List<AplicacaoCobertura>) em.createNamedQuery("getAllAplicacoesCoberturas").getResultList();
    }
}
