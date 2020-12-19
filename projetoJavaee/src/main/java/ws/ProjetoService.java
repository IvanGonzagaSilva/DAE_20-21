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


    private ProjetoDTO toDTO(Projeto projeto) {
        return new ProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                estruturasToDTOs(projeto.getEstruturas()),
                projeto.getProjetista().getUsername(),
                clienteToDTOs(projeto.getClientes())
        );
    }


    private MaterialDTO materialToDTO(Material material) {
        return new MaterialDTO(
                material.getId(),
                material.getTipoDeMaterial()
        );
    }

    private Set<MaterialDTO> materiaisToDTOs(Set<Material> materiais) {
        return materiais.stream().map(this::materialToDTO).collect(Collectors.toSet());
    }

    private AplicacaoDTO aplicacaoCoberturaToDTO(AplicacaoCobertura aplicacao) {
        return new AplicacaoDTO(
                aplicacao.getId(),
                aplicacao.getCargaPermanente(),
                aplicacao.getVentoPressao(),
                aplicacao.getVentoSucao(),
                aplicacao.getAngulo(),
                aplicacao.getSobrecarga(),
                -1,
                aplicacao.getNeve(),
                aplicacao.getTipo()
        );
    }

    private AplicacaoDTO aplicacaoToDTO(Aplicacao aplicacao) {
        return new AplicacaoDTO(
                aplicacao.getId(),
                aplicacao.getCargaPermanente(),
                aplicacao.getVentoPressao(),
                aplicacao.getVentoSucao(),
                -1,
                -1,
                -1,
                -1,
                aplicacao.getTipo()

        );
    }

    private AplicacaoDTO aplicacaoGeralToDTO(AplicacaoGeral aplicacao) {
        return new AplicacaoDTO(
                aplicacao.getId(),
                aplicacao.getCargaPermanente(),
                aplicacao.getVentoPressao(),
                aplicacao.getVentoSucao(),
                aplicacao.getAngulo(),
                aplicacao.getSobrecarga(),
                aplicacao.getCategoriaSobrecarga(),
                aplicacao.getNeve(),
                aplicacao.getTipo()
        );
    }

    private ParametrosCalculoDTO parametrosCalculoToDTO(ParametrosCalculo parametrosCalculo) {
        return new ParametrosCalculoDTO(
                parametrosCalculo.getId(),
                parametrosCalculo.isContraventamentoTotal(),
                parametrosCalculo.getNumeroContraventamentosLaterais(),
                parametrosCalculo.isContribuicaoChapaRevestimento(),
                parametrosCalculo.getNumeroFixacoes(),
                parametrosCalculo.getInerciaChapaRevestimento(),
                parametrosCalculo.getVerificacaoDeformacao(),
                parametrosCalculo.getLimiteDeformacao()
        );
    }

    private VarianteDTO varianteToDTO(Variante variante) {
        return new VarianteDTO(
                variante.getCodigo(),
                variante.getNome(),
                variante.getWeff_p(),
                variante.getWeff_n(),
                variante.getAr(),
                variante.getSigmaC(),
                variante.getPp(),
                variante.getProduto().getNome()
        );
    }


    private List<VarianteDTO> variantesToDTOs(List<Variante> variantes) {
        return variantes.stream().map(this::varianteToDTO).collect(Collectors.toList());
    }

    private EstruturaDTO estruturaToDTO(Estrutura estrutura) {
        AplicacaoDTO aplicacao = null;

        switch (estrutura.getAplicacao().getTipo()) {
            case "Geral":
                aplicacao = aplicacaoGeralToDTO((AplicacaoGeral) estrutura.getAplicacao());
                break;
            case "Cobertura":
                aplicacao = aplicacaoCoberturaToDTO((AplicacaoCobertura) estrutura.getAplicacao());
                break;
            case "Fachada":
                aplicacao = aplicacaoToDTO(estrutura.getAplicacao());
                break;
        }


        return new EstruturaDTO(
                estrutura.getId(),
                estrutura.getNome(),
                geometriaToDTO(estrutura.getGeometria()),
                aplicacao,
                parametrosCalculoToDTO(estrutura.getParametrosCalculo()),
                materiaisToDTOs(estrutura.getMateriais()),
                variantesToDTOs(estrutura.getVariantes())

        );
    }

    private GeometriaDTO geometriaToDTO(Geometria geometria) {
        return new GeometriaDTO(
                geometria.getId(),
                geometria.getNumeroVaos(),
                geometria.getComprimentoVao(),
                geometria.getEspacamentoVigas(),
                familiaToDTOs(geometria.getFamilias())
        );
    }

    private List<FamiliaDTO> familiaToDTOs(List<Familia> familias) {
        return familias.stream().map(this::familiaToDTO).collect(Collectors.toList());
    }

    private FamiliaDTO familiaToDTO(Familia familia) {
        return new FamiliaDTO(
                familia.getNome()
        );
    }

    private Set<EstruturaDTO> estruturasToDTOs(Set<Estrutura> estruturas) {
        return estruturas.stream().map(this::estruturaToDTO).collect(Collectors.toSet());
    }

    private Set<ClienteDTO> clienteToDTOs(Set<Cliente> clientes) {
        return clientes.stream().map(this::clienteToDTO).collect(Collectors.toSet());
    }

    private ClienteDTO clienteToDTO(Cliente cliente) {
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

    private List<ProjetoDTO> projetoToDTOs(List<Projeto> projetos) {
        return projetos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/students/”
    public List<ProjetoDTO> getAllProjetosWS() {
        return projetoToDTOs(projetoBean.getAllProjetos());
    }

    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/{username}") // means: the relative url path is “/api/students/”
    public List<ProjetoDTO> getAllProjetosByClientIdWS(@PathParam("username") String username) {
        return projetoToDTOs(clienteBean.getAllProjetos(username));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProjetoWS(ProjetoDTO projetoDTO) {
        try {

            Projetista projetista = projetistaBean.find(projetoDTO.getUsernameProjetista());

            if (projetista == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            Projeto projeto = projetoBean.create(projetoDTO.getNome(), projetoDTO.getUsernameProjetista());

            return Response.status(Response.Status.CREATED).entity(toDTO(projeto)).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeProjetoWS(@PathParam("id") int idProjeto) {
        try {
          projetoBean.delete(idProjeto);

          return Response.status(Response.Status.OK).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarProjetoWS(ProjetoDTO projetoDTO) {
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
    public Response adicionarEstruturaAoProjetoWS(@PathParam("id") int idProjeto, EstruturaDTO estruturaDTO) {
        try {

            projetoBean.addEstrutura(idProjeto, estruturaDTO.getId());

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/remove/estrutura")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removerEstruturaAoProjetoWS(@PathParam("id") int idProjeto, EstruturaDTO estruturaDTO) {
        try {

            projetoBean.removeEstrutura(idProjeto, estruturaDTO.getId());

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/enrollclients")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response enrollClientProjetoWS(@PathParam("id") int idProjeto, List<ClienteDTO> clientesDTO) {
        try {

            for (ClienteDTO clienteDTO : clientesDTO) {
                projetoBean.enrollCliente(idProjeto, clienteDTO.getUsername());

            }

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


    @PUT
    @Path("{id}/unrollclient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response unrollClientProjetoWS(@PathParam("id") int idProjeto, ClienteDTO clienteDTO) {
        try {

            projetoBean.unrollCliente(idProjeto, clienteDTO.getUsername());

            return Response.status(Response.Status.OK).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


}
