package ejbs;

import dtos.EstruturaDTO;
import dtos.ProjetoDTO;
import entities.Cliente;
import entities.Estrutura;
import entities.Projeto;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless(name = "ProjetoEJB")
public class ProjetoBean {

    @PersistenceContext
    EntityManager em;
    @EJB
    ClienteBean clienteBean;
    @EJB
    EstruturaBean estruturaBean;

    public ProjetoBean() {
    }

    public Projeto create(String nome, String emailCliente) throws MyEntityNotFoundException, MyConstraintViolationException {

        Cliente cliente = em.find(Cliente.class, emailCliente);

        if(cliente == null){
            throw new MyEntityNotFoundException();
        }

        try {
            Projeto projeto = new Projeto(nome, cliente);

            cliente.addProjeto(projeto);

            em.persist(projeto);

            return projeto;

        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }

    }

    public List<Projeto> getAllProjetos() {
        return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
    }

    public Projeto find(int idProjeto) throws MyEntityNotFoundException {
        Projeto projeto = em.find(Projeto.class, idProjeto);

        if(projeto == null){
            throw new MyEntityNotFoundException();
        }

        return projeto;
    }

    public void delete(int idprojeto) throws MyEntityNotFoundException {
        Projeto projeto = find(idprojeto);

        if(projeto != null){
            em.remove(projeto);
        }
    }


    public void update(ProjetoDTO projetoDTO) throws MyEntityNotFoundException {
        Projeto projeto = find(projetoDTO.getId());

        if (projeto!=null && projeto.getCliente()!=null && projeto.getNome()!=null){
            projeto.setCliente(clienteBean.find(projetoDTO.getCliente().getEmail()));
            projeto.setNome(projetoDTO.getNome());
        }
    }

    public void addEstrutura(int idProjeto, EstruturaDTO estruturaDTO) throws MyEntityNotFoundException, MyConstraintViolationException {
        Projeto projeto = find(idProjeto);

        if(projeto == null){
            throw new MyEntityNotFoundException();
        }

        Estrutura estrutura = estruturaBean.find(estruturaDTO.getId());

        if(estrutura == null){
            throw new MyEntityNotFoundException();
        }

        try {
            projeto.addEstruturas(estrutura);

        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }


    }

    public void removeEstrutura(int idProjeto, EstruturaDTO estruturaDTO) throws MyEntityNotFoundException, MyConstraintViolationException {
        Projeto projeto = find(idProjeto);

        if(projeto == null){
            throw new MyEntityNotFoundException();
        }

        Estrutura estrutura = estruturaBean.find(estruturaDTO.getId());

        if(estrutura == null){
            throw new MyEntityNotFoundException();
        }

        try {
            projeto.removeEstruturas(estrutura);

        }catch (ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }


    }

}
