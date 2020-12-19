package ejbs;

import dtos.ProjetoDTO;
import entities.Cliente;
import entities.Estrutura;
import entities.Projetista;
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
    @EJB
    ProjetistaBean projetistaBean;

    public ProjetoBean() {
    }

    public Projeto create(String nome, String usernameProjetista) throws MyEntityNotFoundException, MyConstraintViolationException {

        Projetista projetista = projetistaBean.find(usernameProjetista);

        if (projetista == null) {
            throw new MyEntityNotFoundException();
        }

        try {
            Projeto projeto = new Projeto(nome, projetista);

            em.persist(projeto);

            projetista.addProjeto(projeto);

            return projeto;

        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }

    }

    public List<Projeto> getAllProjetos() {
        return em.createNamedQuery("getAllProjetos", Projeto.class).getResultList();
    }

    public Projeto find(int idProjeto) throws MyEntityNotFoundException {
        Projeto projeto = em.find(Projeto.class, idProjeto);

        if (projeto == null) {
            throw new MyEntityNotFoundException();
        }

        return projeto;
    }

    public void delete(int idprojeto) throws MyEntityNotFoundException {
        Projeto projeto = find(idprojeto);

        if (projeto != null) {
            em.remove(projeto);
        }
    }


    public void update(ProjetoDTO projetoDTO) throws MyEntityNotFoundException {
        Projeto projeto = find(projetoDTO.getId());

        if (projeto == null) {
            throw new MyEntityNotFoundException();
        }

        Projetista projetista = projetistaBean.find(projetoDTO.getUsernameProjetista());

        if (projetista == null) {
            throw new MyEntityNotFoundException();
        }

        projeto.setProjetista(projetista);
        projeto.setNome(projetoDTO.getNome());


    }

    public void addEstrutura(int idProjeto, int idEstrutura) throws MyEntityNotFoundException, MyConstraintViolationException {
        Projeto projeto = find(idProjeto);

        if (projeto == null) {
            throw new MyEntityNotFoundException();
        }

        Estrutura estrutura = estruturaBean.find(idEstrutura);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        try {
            projeto.addEstruturas(estrutura);

        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }


    }

    public void removeEstrutura(int idProjeto, int idEstrutura) throws MyEntityNotFoundException, MyConstraintViolationException {
        Projeto projeto = find(idProjeto);

        if (projeto == null) {
            throw new MyEntityNotFoundException();
        }

        Estrutura estrutura = estruturaBean.find(idEstrutura);

        if (estrutura == null) {
            throw new MyEntityNotFoundException();
        }

        try {
            projeto.removeEstruturas(estrutura);

        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public void enrollCliente(int idProjeto, String usernameCliente) throws MyEntityNotFoundException {
        Projeto projeto = this.find(idProjeto);

        if (projeto == null) {
            throw new MyEntityNotFoundException();
        }

        Cliente cliente = clienteBean.find(usernameCliente);

        if (cliente == null) {
            throw new MyEntityNotFoundException();
        }

        cliente.addProjeto(projeto);
        projeto.addCliente(cliente);
    }


    public void unrollCliente(int idProjeto, String clientUsername) throws MyEntityNotFoundException {
        Projeto projeto = this.find(idProjeto);

        if (projeto == null) {
            throw new MyEntityNotFoundException();
        }

        Cliente cliente = clienteBean.find(clientUsername);

        if (cliente == null) {
            throw new MyEntityNotFoundException();
        }

        cliente.removeProjeto(projeto);

    }
}
