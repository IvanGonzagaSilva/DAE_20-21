package ws;

import dtos.*;
import ejbs.EstruturaBean;
import entities.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/estrutura") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”


public class EstruturaService {

    private static final Logger log = Logger.getLogger(LoginService.class.getName());

    @EJB
    private EstruturaBean estruturaBean;

    private EstruturaDTO toDTO(Estrutura estrutura) {

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

    //Verificar com o prof, porque pode ser varios tipos de aplicacao
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

    private MaterialDTO materialToDTO(Material material) {
        return new MaterialDTO(
                material.getId(),
                material.getTipoDeMaterial()
        );
    }

    private List<ProdutoDTO> produtosToDTOs(List<Produto> produtos) {
        return produtos.stream().map(this::produtoToDTO).collect(Collectors.toList());
    }

    private ProdutoDTO produtoToDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getNome(),
                variantesToDTOs(produto.getVariantes())
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

    private List<EstruturaDTO> estruturaToDTOs(List<Estrutura> estruturas) {
        return estruturas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<EstruturaDTO> getAllEstruturasWS() {
        List<EstruturaDTO> estruturaDTOS = estruturaToDTOs(estruturaBean.getAllEstruturas());
        return estruturaDTOS;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEstruturaWS(EstruturaDTO estruturaDTO) {
        try {
            Estrutura estrutura = estruturaBean.create(
                    estruturaDTO.getNome(),
                    estruturaDTO.getGeometria().getId(),
                    estruturaDTO.getAplicacao().getId(),
                    estruturaDTO.getParametrosCalculo().getId());

            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeEstruturaWS(EstruturaDTO estruturaDTO) {
        try {
            estruturaBean.delete(estruturaDTO.getId());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEstruturaWS(EstruturaDTO estruturaDTO) {
        try {
            estruturaBean.update(
                    estruturaDTO.getId(),
                    estruturaDTO.getNome(),
                    estruturaDTO.getGeometria().getId(),
                    estruturaDTO.getAplicacao().getId(),
                    estruturaDTO.getParametrosCalculo().getId());

            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/add/material")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarMaterialAEstruturaWS(@PathParam("id") int idEstrutura, MaterialDTO materialDTO) {
        try {
            estruturaBean.addMaterial(idEstrutura, materialDTO.getId());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/remove/material")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removerMaterialAEstruturaWS(@PathParam("id") int idEstrutura, MaterialDTO materialDTO) {
        try {
            estruturaBean.addMaterial(idEstrutura, materialDTO.getId());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/add/variante")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarMaterialAEstruturaWS(@PathParam("id") int idEstrutura, VarianteDTO varianteDTO) {
        try {
            estruturaBean.addVariante(idEstrutura, varianteDTO.getCodigo());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/remove/variante")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removerMaterialAEstruturaWS(@PathParam("id") int idEstrutura, VarianteDTO varianteDTO) {
        try {
            estruturaBean.addVariante(idEstrutura, varianteDTO.getCodigo());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
