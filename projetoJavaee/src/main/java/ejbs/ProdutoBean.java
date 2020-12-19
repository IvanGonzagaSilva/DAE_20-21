package ejbs;

import entities.Produto;
import entities.Variante;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class ProdutoBean {

    @PersistenceContext
    private EntityManager em;

    public Produto create(String nome) throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {
        try {
          Produto produto = em.find(Produto.class, nome);

          if (produto != null) {
            throw new MyEntityExistsException();
          }

            produto = new Produto(nome);
            em.persist(produto);
            return produto;

        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public Produto update(String nome) {

        Produto produto = em.find(Produto.class, nome);

        if (produto == null)
            throw new EntityNotFoundException("Produto not found.");

        em.lock(produto, LockModeType.OPTIMISTIC);
        produto.setNome(nome);
        em.merge(produto);

        return produto;
    }

    public Produto find(String nome)
            throws MyEntityNotFoundException {

        Produto produto = em.find(Produto.class, nome);

        if (produto == null)
            throw new MyEntityNotFoundException("Produto not found.");

        return produto;
    }

    public void delete(String nomeProduto) throws MyEntityNotFoundException {
        Produto produto = find(nomeProduto);

        if (produto != null) {
            em.remove(produto);
        }
    }

    public List<Produto> getAllProdutos() {
        return (List<Produto>) em.createNamedQuery("getAllProdutos").getResultList();
    }

    public void addVariante(String nomeProduto, int idVariante) throws MyEntityNotFoundException {
        Produto produto = em.find(Produto.class, nomeProduto);

        if (produto == null) {
            throw new MyEntityNotFoundException();
        }

        Variante variante = em.find(Variante.class, idVariante);

        if (variante == null) {
            throw new MyEntityNotFoundException();
        }

        if (!produto.getVariantes().contains(variante)) {
            produto.addVariante(variante);
        }
    }

    public void removeVariante(String nomeProduto, int idVariante) throws MyEntityNotFoundException {
        Produto produto = em.find(Produto.class, nomeProduto);

        if (produto == null) {
            throw new MyEntityNotFoundException();
        }

        Variante variante = em.find(Variante.class, idVariante);

        if (variante == null) {
            throw new MyEntityNotFoundException();
        }

        if (produto.getVariantes().contains(variante)) {
            produto.removeVariante(variante);
        }
    }
}
