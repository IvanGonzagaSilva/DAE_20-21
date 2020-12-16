package ejbs;

import entities.Aplicacao;
import entities.Material;
import entities.Produto;
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
public class ProdutoBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int id, String nome)
            throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {

        Produto produto = em.find(Produto.class, id);

        if (produto != null)
            throw new MyEntityExistsException("Produto with id: " + id + " already exists");

        try {
            produto = new Produto(id, nome);
            em.persist(produto);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Produto update(int id, String nome, Material material) {

        Produto produto = em.find(Produto.class, id);

        if (produto == null)
            throw new EntityNotFoundException("Produto not found.");

        em.lock(produto, LockModeType.OPTIMISTIC);
        produto.setNome(nome);
        em.merge(produto);

        return produto;
    }

    public Produto findProduto(Integer id)
            throws MyEntityNotFoundException {

        Produto produto = em.find(Produto.class, id);

        if (produto == null)
            throw new MyEntityNotFoundException("Produto not found.");

        return produto;
    }

    public List<Produto> getAllProdutos() {
        return (List<Produto>) em.createNamedQuery("getAllProdutos").getResultList();
    }
}
