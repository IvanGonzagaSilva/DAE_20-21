package ws;


import dtos.ClienteDTO;
import dtos.PessoaDeContactoDTO;
import dtos.ProjetistaDTO;
import dtos.ProjetoDTO;
import ejbs.ClienteBean;
import ejbs.PessoaDeContactoBean;
import ejbs.ProjetistaBean;
import ejbs.ProjetoBean;
import entities.Cliente;
import entities.PessoaDeContacto;
import entities.Projetista;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

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

            clienteBean.create(clienteDTO.getNome(), clienteDTO.getPc().getUsername(), clienteDTO.getMorada(), clienteDTO.getEmail(), clienteDTO.getUsername(), clienteDTO.getPassword());


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


}
