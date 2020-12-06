package ws;

import dtos.*;
import ejbs.ProjetoBean;
import entities.*;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/projeto") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”


public class ProjetoService {

    @EJB
    private ProjetoBean projetoBean;

    private ProjetoDTO toDTO(Projeto projeto){
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                clienteToDTO(projeto.getCliente()),
                estruturasToDTOs(projeto.getEstruturas())
        );
    }


    private MaterialDTO materialToDTO(Material material){
        return new MaterialDTO(
                material.getTipoDeMaterial()
        );
    }

    private Set<MaterialDTO> materiaisToDTOs(Set<Material> materiais){
        return materiais.stream().map(this::materialToDTO).collect(Collectors.toSet());
    }

    private EstruturaDTO estruturaToDTO(Estrutura estrutura){
        return new EstruturaDTO(
                estrutura.getId(),
                materiaisToDTOs(estrutura.getMateriais())
        );
    }

    private Set<EstruturaDTO> estruturasToDTOs(Set<Estrutura> estruturas){
        return estruturas.stream().map(this::estruturaToDTO).collect(Collectors.toSet());
    }

    private ClienteDTO clienteToDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getNome(),
                pessoaDeContactoToDTO(cliente.getPc()),
                cliente.getMorada(),
                cliente.getEmail()
        );
    }

    private PessoaDeContactoDTO pessoaDeContactoToDTO(PessoaDeContacto pessoaDeContacto){
        return new PessoaDeContactoDTO(
                pessoaDeContacto.getUsername(),
                pessoaDeContacto.getEmail(),
                pessoaDeContacto.getNome(),
                pessoaDeContacto.getContactoTelefonico()
        );
    }

    private List<ProjetoDTO> projetoToDTOs(List<Projeto> projetos){
        return projetos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/students/”
    public List<ProjetoDTO> getAllProjetosWS() {
        return projetoToDTOs(projetoBean.getAllProjetos());
    }


}
