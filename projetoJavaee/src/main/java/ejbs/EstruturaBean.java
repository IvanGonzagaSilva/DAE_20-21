package ejbs;

import entities.Estrutura;
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

    public void create(Integer id, String nome, Integer numVaos, Integer comprimentoVao,
                       Integer espacamentoVigas, String parametrosCalculo, Integer idMaterial)
            throws MyEntityExistsException, MyConstraintViolationException {

        Material material = em.find(Material.class, idMaterial);

        if (material == null)
            throw new EntityNotFoundException("Material not found.");

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura != null)
            throw new MyEntityExistsException("Estrutura with id: " + id + " already exists");

        try {
            estrutura = new Estrutura(id, nome, numVaos, comprimentoVao, espacamentoVigas, parametrosCalculo, material);
            em.persist(estrutura);
        }
        catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Estrutura update(Integer id, String nome, Integer numVaos, Integer comprimentoVao,
                            Integer espacamentoVigas, String parametrosCalculo, Material material) {

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura == null)
            throw new EntityNotFoundException("Estrutura not found.");

        em.lock(estrutura, LockModeType.OPTIMISTIC);
        estrutura.setNome(nome);
        estrutura.setNumVaos(numVaos);
        estrutura.setComprimentoVao(comprimentoVao);
        estrutura.setEspacamentoVigas(espacamentoVigas);
        estrutura.setParametrosCalculo(parametrosCalculo);
        estrutura.setMaterial(material);
        em.merge(estrutura);

        return estrutura;
    }

    public Estrutura findEstrutura(Integer id) throws MyEntityNotFoundException {
        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura == null)
            throw new MyEntityNotFoundException("Estrutura not found.");

        return estrutura;
    }

    public List<Estrutura> getAllEstruturas() {
        return (List<Estrutura>) em.createNamedQuery("getAllEstruturas").getResultList();
    }
}
