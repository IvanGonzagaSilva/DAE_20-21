package ws;


import dtos.*;
import ejbs.ClienteBean;
import ejbs.PessoaDeContactoBean;
import ejbs.ProjetistaBean;
import ejbs.ProjetoBean;
import entities.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/user") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class UserService {

    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());

    @EJB
    private ProjetistaBean projetistaBean;
    @EJB
    private ClienteBean clienteBean;
    @EJB
    private PessoaDeContactoBean pessoaDeContactoBean;


    @POST
    @Path("/create/projetista")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProjetistaWS(ProjetistaDTO projetistaDTO) {
        try {

            projetistaBean.create(projetistaDTO.getUsername(), projetistaDTO.getEmail(), projetistaDTO.getNome(), projetistaDTO.getContactoTelefonico(), projetistaDTO.getPassword());

            Projetista projetista = projetistaBean.find(projetistaDTO.getUsername());


            if(projetista == null){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }


            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/create/pessoacontacto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPessoaContactoWS(PessoaDeContactoDTO pessoaDeContactoDTO) {
        try {

            pessoaDeContactoBean.create(pessoaDeContactoDTO.getUsername(), pessoaDeContactoDTO.getEmail(), pessoaDeContactoDTO.getNome(), pessoaDeContactoDTO.getContactoTelefonico(), pessoaDeContactoDTO.getPassword());


            PessoaDeContacto pc = pessoaDeContactoBean.find(pessoaDeContactoDTO.getUsername());


            if(pc == null){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }


            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/create/cliente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClienteWS(ClienteDTO clienteDTO) {
        try {

            clienteBean.create(clienteDTO.getNome(), clienteDTO.getPc().getUsername(), clienteDTO.getMorada(), clienteDTO.getEmail(), clienteDTO.getUsername(), clienteDTO.getPassword(), clienteDTO.getContactoTelefonico());


            Cliente c = clienteBean.find(clienteDTO.getEmail());


            if(c == null){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }


            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

  @GET
  @Path("/cliente/all")
  public List<ClienteDTO> getAllClientesWS() {
    List<ClienteDTO> clientesDTOS = clientesToDTOs(clienteBean.getAllClientes());
    return clientesDTOS;
  }

  private List<ClienteDTO> clientesToDTOs(List<Cliente> clientes) {
    return clientes.stream().map(this::clienteToDto).collect(Collectors.toList());
  }

  private ClienteDTO clienteToDto(Cliente cliente) {
    return new ClienteDTO(
      cliente.getUsername(),
      cliente.getEmail(),
      cliente.getNome(),
      cliente.getContactoTelefonico(),
      cliente.getPassword(),
      pessoaDeContactoToDTO(cliente.getPc()),
      cliente.getMorada()
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


}
