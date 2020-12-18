package ejbs;

import entities.ParametrosCalculo;
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
public class ParametrosCalculoBean {

    @PersistenceContext
    private EntityManager em;

    public ParametrosCalculo create(boolean contraventamentoTotal, String verificacaoDeformacao, int limiteDeformacao)
            throws MyEntityExistsException, MyConstraintViolationException {

        try {
            ParametrosCalculo parametrosCalculo = new ParametrosCalculo(contraventamentoTotal, verificacaoDeformacao, limiteDeformacao);
            em.persist(parametrosCalculo);
            return parametrosCalculo;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public ParametrosCalculo create(boolean contraventamentoTotal, int numeroContraventamentosLaterais, boolean contribuicaoChapaRevestimento, String verificacaoDeformacao, int limiteDeformacao)
            throws MyEntityExistsException, MyConstraintViolationException {


        try {
            ParametrosCalculo parametrosCalculo = new ParametrosCalculo(contraventamentoTotal, numeroContraventamentosLaterais, contribuicaoChapaRevestimento, verificacaoDeformacao, limiteDeformacao);
            em.persist(parametrosCalculo);
            return parametrosCalculo;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public ParametrosCalculo create(boolean contraventamentoTotal, int numeroContraventamentosLaterais, boolean contribuicaoChapaRevestimento, int numeroFixacoes, int inerciaChapaRevestimento, String verificacaoDeformacao, int limiteDeformacao)
            throws MyEntityExistsException, MyConstraintViolationException {

        try {
            ParametrosCalculo parametrosCalculo = new ParametrosCalculo(contraventamentoTotal, numeroContraventamentosLaterais, contribuicaoChapaRevestimento, numeroFixacoes, inerciaChapaRevestimento, verificacaoDeformacao, limiteDeformacao);
            em.persist(parametrosCalculo);
            return parametrosCalculo;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public ParametrosCalculo update(int id, boolean contraventamentoTotal, String verificacaoDeformacao, int limiteDeformacao) {

        ParametrosCalculo parametrosCalculo = em.find(ParametrosCalculo.class, id);

        if (parametrosCalculo == null)
            throw new EntityNotFoundException("Parametros de Calculo not found.");

        em.lock(parametrosCalculo, LockModeType.OPTIMISTIC);
        parametrosCalculo.setContraventamentoTotal(contraventamentoTotal);
        parametrosCalculo.setVerificacaoDeformacao(verificacaoDeformacao);
        parametrosCalculo.setLimiteDeformacao(limiteDeformacao);
        em.merge(parametrosCalculo);

        return parametrosCalculo;
    }

    public ParametrosCalculo update(int id, boolean contraventamentoTotal, int numeroContraventamentosLaterais, boolean contribuicaoChapaRevestimento, String verificacaoDeformacao, int limiteDeformacao) {

        ParametrosCalculo parametrosCalculo = em.find(ParametrosCalculo.class, id);

        if (parametrosCalculo == null)
            throw new EntityNotFoundException("Parametros de Calculo not found.");

        em.lock(parametrosCalculo, LockModeType.OPTIMISTIC);
        parametrosCalculo.setContraventamentoTotal(contraventamentoTotal);
        parametrosCalculo.setNumeroContraventamentosLaterais(numeroContraventamentosLaterais);
        parametrosCalculo.setContribuicaoChapaRevestimento(contribuicaoChapaRevestimento);
        parametrosCalculo.setVerificacaoDeformacao(verificacaoDeformacao);
        parametrosCalculo.setLimiteDeformacao(limiteDeformacao);
        em.merge(parametrosCalculo);

        return parametrosCalculo;
    }

    public ParametrosCalculo update(int id, boolean contraventamentoTotal, int numeroContraventamentosLaterais, boolean contribuicaoChapaRevestimento, int numeroFixacoes, int inerciaChapaRevestimento, String verificacaoDeformacao, int limiteDeformacao) {

        ParametrosCalculo parametrosCalculo = em.find(ParametrosCalculo.class, id);

        if (parametrosCalculo == null)
            throw new EntityNotFoundException("Parametros de Calculo not found.");

        em.lock(parametrosCalculo, LockModeType.OPTIMISTIC);
        parametrosCalculo.setContraventamentoTotal(contraventamentoTotal);
        parametrosCalculo.setNumeroContraventamentosLaterais(numeroContraventamentosLaterais);
        parametrosCalculo.setContribuicaoChapaRevestimento(contribuicaoChapaRevestimento);
        parametrosCalculo.setNumeroFixacoes(numeroFixacoes);
        parametrosCalculo.setInerciaChapaRevestimento(inerciaChapaRevestimento);
        parametrosCalculo.setVerificacaoDeformacao(verificacaoDeformacao);
        parametrosCalculo.setLimiteDeformacao(limiteDeformacao);
        em.merge(parametrosCalculo);

        return parametrosCalculo;
    }

    public ParametrosCalculo findParametrosCalculo(Integer id)
            throws MyEntityNotFoundException {

        ParametrosCalculo parametrosCalculo = em.find(ParametrosCalculo.class, id);

        if (parametrosCalculo == null)
            throw new MyEntityNotFoundException("Parametros de Calculo not found.");

        return parametrosCalculo;
    }

    public List<ParametrosCalculo> getAllParametrosCalculo() {
        return (List<ParametrosCalculo>) em.createNamedQuery("getAllParametrosCalculo").getResultList();
    }
}
