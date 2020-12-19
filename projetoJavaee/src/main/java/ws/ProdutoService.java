package ws;

import dtos.ProdutoDTO;
import dtos.VarianteDTO;
import ejbs.ProdutoBean;
import entities.Produto;
import entities.Variante;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/produto")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})

public class ProdutoService {

    private static final Logger log = Logger.getLogger(LoginService.class.getName());

    @EJB
    private ProdutoBean produtoBean;

    private ProdutoDTO toDTO(Produto produto) {
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

    private List<ProdutoDTO> produtoToDTOs(List<Produto> produtos) {
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<ProdutoDTO> getAllProdutosWS() {
        List<ProdutoDTO> produtoDTOS = produtoToDTOs(produtoBean.getAllProdutos());
        return produtoDTOS;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProdutoWS(ProdutoDTO produtoDTO) {
        try {
            produtoBean.create(produtoDTO.getNome());

            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeProdutoWS(ProdutoDTO produtoDTO) {
        try {

            produtoBean.delete(produtoDTO.getNome());

            return Response.status(Response.Status.OK).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarProdutoWS(ProdutoDTO produtoDTO) {
        try {
            produtoBean.update(produtoDTO.getNome());

            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{nome}/add/variante/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response adicionarVarianteAoProdutoWS(@PathParam("nome") String nomeProduto, @PathParam("id") int codigo) {
        try {
            produtoBean.addVariante(nomeProduto, codigo);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @PUT
    @Path("{id}/remove/variante")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removerVarianteAoProdutoWS(@PathParam("id") String nomeProduto, VarianteDTO varianteDTO) {
        try {
            produtoBean.removeVariante(nomeProduto, varianteDTO.getCodigo());
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
