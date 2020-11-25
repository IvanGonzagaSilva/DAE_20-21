package ejbs;

import entities.Estrutura;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EstruturaBean {

    @PersistenceContext
    private EntityManager em;

    public void create(Integer id, String nome, String tipoMaterial, Integer numVaos, Integer comprimentoVao,
                       Integer espacamentoVigas, String parametrosCalculo) {

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura == null) {
            estrutura = new Estrutura(id, nome, tipoMaterial, numVaos, comprimentoVao, espacamentoVigas, parametrosCalculo);
            em.persist(estrutura);
        }
    }

    public Estrutura update(Integer id, String nome, String tipoMaterial, Integer numVaos, Integer comprimentoVao,
                            Integer espacamentoVigas, String parametrosCalculo) {

        Estrutura estrutura = em.find(Estrutura.class, id);

        if (estrutura != null) {
            em.lock(estrutura, LockModeType.OPTIMISTIC);

            estrutura.setNome(nome);
            estrutura.setTipoMaterial(tipoMaterial);
            estrutura.setNumVaos(numVaos);
            estrutura.setComprimentoVao(comprimentoVao);
            estrutura.setEspacamentoVigas(espacamentoVigas);
            estrutura.setParametrosCalculo(parametrosCalculo);
        }

        em.merge(estrutura);
        return estrutura;
    }

    public List<Estrutura> getAllEstruturas() {
        return (List<Estrutura>) em.createNamedQuery("getAllEstruturas").getResultList();
    }

    public Estrutura findEstrutura(Integer id) {
        return em.find(Estrutura.class, id);
    }
}
