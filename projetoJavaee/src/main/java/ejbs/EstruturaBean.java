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
public class EstruturaBean {

    @PersistenceContext
    private EntityManager em;

    public Estrutura create(String nome, int idGeometria, int idAplicacao, int idParametrosCalculo)
            throws MyEntityExistsException, MyConstraintViolationException {

        Geometria geometria = em.find(Geometria.class, idGeometria);
        Aplicacao aplicacao = em.find(Aplicacao.class, idAplicacao);
        ParametrosCalculo parametrosCalculo = em.find(ParametrosCalculo.class, idParametrosCalculo);

        if (geometria == null || aplicacao == null || parametrosCalculo == null)
            throw new EntityNotFoundException("Geometria, Aplicação ou Parametros de Calculo  not found.");


        try {
            Estrutura estrutura = new Estrutura(nome, geometria, aplicacao, parametrosCalculo);
            em.persist(estrutura);
            return estrutura;
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Estrutura update(int id, String nome, int idGeometria, int idAplicacao, int idParametrosCalculo) {

        Geometria geometria = em.find(Geometria.class, idGeometria);
        Aplicacao aplicacao = em.find(Aplicacao.class, idAplicacao);
        ParametrosCalculo parametrosCalculo = em.find(ParametrosCalculo.class, idParametrosCalculo);

        if (geometria == null || aplicacao == null || parametrosCalculo == null)
            throw new EntityNotFoundException("Geometria, Aplicação or Parametros de Calculo not found.");

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura == null)
            throw new EntityNotFoundException("Estrutura not found.");

        em.lock(estrutura, LockModeType.OPTIMISTIC);
        estrutura.setNome(nome);
        estrutura.setGeometria(geometria);
        estrutura.setAplicacao(aplicacao);
        estrutura.setParametrosCalculo(parametrosCalculo);
        em.merge(estrutura);

        return estrutura;
    }

    public Estrutura find(int id)
            throws MyEntityNotFoundException {

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura == null)
            throw new MyEntityNotFoundException("Estrutura not found.");

        return estrutura;
    }

    public void delete(int idEstrutura) throws MyEntityNotFoundException {
        Estrutura estrutura = find(idEstrutura);

        if (estrutura != null) {
            em.remove(estrutura);
        }
    }

    public List<Estrutura> getAllEstruturas() {
        return (List<Estrutura>) em.createNamedQuery("getAllEstruturas").getResultList();
    }

    public void addVariante(int idEstrutura, int idVariante) throws MyEntityNotFoundException {
        Estrutura estrutura = find(idEstrutura);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        Variante variante = em.find(Variante.class, idVariante);

        if (variante == null) {
            throw new MyEntityNotFoundException();
        }

        estrutura.addVariante(variante);
    }

    public void removeVariante(int idEstrutura, int idVariante) throws MyEntityNotFoundException {
        Estrutura estrutura = find(idEstrutura);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        Variante variante = em.find(Variante.class, idVariante);

        if (variante == null) {
            throw new MyEntityNotFoundException();
        }

        estrutura.removeVariante(variante);
    }

    public void addMaterial(int idEstrutura, int idMaterial) throws MyEntityNotFoundException {
        Estrutura estrutura = find(idEstrutura);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        Material material = em.find(Material.class, idMaterial);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        estrutura.addMaterial(material);
    }

    public void removeMaterial(int idEstrutura, int idMaterial) throws MyEntityNotFoundException {
        Estrutura estrutura = find(idEstrutura);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        Material material = em.find(Material.class, idMaterial);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        estrutura.removeMaterial(material);
    }
}
