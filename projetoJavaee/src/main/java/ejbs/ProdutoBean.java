package ejbs;

import dtos.ProdutoDTO;
import dtos.VarianteDTO;
import entities.Produto;
import entities.Variante;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
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
  @EJB
  VarianteBean varianteBean;

  public ProdutoBean() {
  }

  public void create(String nome) throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {
    try {
      Produto produto = new Produto(nome);
      em.persist(produto);
    } catch (ConstraintViolationException e) {
      throw new MyConstraintViolationException(e);
    }
  }

  public Produto update(ProdutoDTO produtoDTO) {

    Produto produto = em.find(Produto.class, produtoDTO.getNome());

    if (produto == null)
      throw new EntityNotFoundException("Produto not found.");

    em.lock(produto, LockModeType.OPTIMISTIC);
    produto.setNome(produtoDTO.getNome());
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

  public void addVariante(String nomeProduto, VarianteDTO varianteDTO) throws MyEntityNotFoundException {
    Produto produto = em.find(Produto.class, nomeProduto);

    if (produto == null) {
      throw new MyEntityNotFoundException();
    }

    Variante variante = varianteBean.find(varianteDTO.getCodigo());

    if(variante == null){
      throw new MyEntityNotFoundException();
    }

    if (!produto.getVariantes().contains(variante)) {
      produto.addVariante(variante);
    }
  }

  public void removeVariante(String nomeProduto, VarianteDTO varianteDTO) throws MyEntityNotFoundException {
    Produto produto = em.find(Produto.class, nomeProduto);

    if (produto == null) {
      throw new MyEntityNotFoundException();
    }

    Variante variante = varianteBean.find(varianteDTO.getCodigo());

    if(variante == null){
      throw new MyEntityNotFoundException();
    }

    if (produto.getVariantes().contains(variante)) {
      produto.removeVariante(variante);
    }
  }
}
