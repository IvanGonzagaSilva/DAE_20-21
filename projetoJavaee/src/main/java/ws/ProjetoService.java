package ws;

import dtos.*;
import ejbs.ClienteBean;
import ejbs.ProjetistaBean;
import ejbs.ProjetoBean;
import entities.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import java.util.stream.Collectors;

@Path("/projeto") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class ProjetoService {

    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());

    @EJB
    private ProjetoBean projetoBean;

    @EJB
    private ClienteBean clienteBean;

    @EJB
    private ProjetistaBean projetistaBean;



    private ProjetoDTO toDTO(Projeto projeto){
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                estruturasToDTOs(projeto.getEstruturas()),
                projeto.getProjetista().getUsername()
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

    private PessoaDeContactoDTO pessoaDeContactoToDTO(PessoaDeContacto pessoaDeContacto) {
        return new PessoaDeContactoDTO(
                pessoaDeContacto.getUsername(),
                pessoaDeContacto.getEmail(),
                pessoaDeContacto.getNome(),
                pessoaDeContacto.getContactoTelefonico(),
                pessoaDeContacto.getPassword()
        );
    }

    private List<ProjetoDTO> projetoToDTOs(List<Projeto> projetos) {
        return projetos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/students/”
    public List<ProjetoDTO> getAllProjetosWS() {
        return projetoToDTOs(projetoBean.getAllProjetos());
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProjetoWS(ProjetoDTO projetoDTO) {
        try {

            Projetista projetista = projetistaBean.find(projetoDTO.getUsernameProjetista());

            if(projetista == null){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Projeto projeto = projetoBean.create(projetoDTO.getNome(), projetoDTO.getUsernameProjetista());

            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeProjetoWS(ProjetoDTO projetoDTO){
        try {

            projetoBean.delete(projetoDTO.getId());

            return Response.status(Response.Status.OK).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarProjetoWS(ProjetoDTO projetoDTO){
        try {

            projetoBean.update(projetoDTO);

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/add/estrutura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarEstruturaAoProjetoWS(@PathParam("id") int idProjeto, EstruturaDTO estruturaDTO){
        try {

            projetoBean.addEstrutura(idProjeto, estruturaDTO);

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/remove/estrutura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removerEstruturaAoProjetoWS(@PathParam("id") int idProjeto, EstruturaDTO estruturaDTO){
        try {

            projetoBean.removeEstrutura(idProjeto, estruturaDTO);

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/enrollclient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enrollClientProjetoWS(@PathParam("id") int idProjeto, ClienteDTO clienteDTO){
        try {

            projetoBean.enrollCliente(idProjeto, clienteDTO.getEmail());

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


    @PUT
    @Path("{id}/unrollclient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response unrollClientProjetoWS(@PathParam("id") int idProjeto, ClienteDTO clienteDTO){
        try {

            projetoBean.unrollCliente(idProjeto, clienteDTO.getEmail());

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


}
