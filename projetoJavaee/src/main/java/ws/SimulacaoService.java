package ws;

import dtos.AplicacaoDTO;
import dtos.GeometriaDTO;
import dtos.ProdutoDTO;
import dtos.VarianteDTO;
import ejbs.*;
import entities.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/simulacao")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})

public class SimulacaoService {
  private static final Logger log = Logger.getLogger(LoginService.class.getName());

  @EJB
  private SimulacaoBean simulacaoBean;
  @EJB
  private ProdutoBean produtoBean;
  @EJB
  private AplicacaoGeralBean aplicacaoGeralBean;
  @EJB
  private GeometriaBean geometriaBean;

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

  private List<ProdutoDTO> produtosToDTOs(List<Produto> produtos) {
    return produtos.stream().map(this::produtoToDTO).collect(Collectors.toList());
  }

  private ProdutoDTO produtoToDTO(Produto produto) {
    return new ProdutoDTO(
      produto.getNome(),
      variantesToDTOs(produto.getVariantes())
    );
  }

  @GET
  @Path("/{idapli}/{idgeo}")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response simulacaoWS(@PathParam("idapli") int idAplicacao, @PathParam("idgeo") int idGeometria) {
    try {
      AplicacaoGeral aplicacao = aplicacaoGeralBean.findAplicacao(idAplicacao);
      Geometria geometria = geometriaBean.findGeometria(idGeometria);

      List<Produto> produtos = produtoBean.getAllProdutos();
      List<Produto> resultado = new java.util.ArrayList<>(Collections.emptyList());

      for (Produto produto : produtos) {

        for (Variante variante : produto.getVariantes()) {

          if (simulacaoBean.simulaVariante(geometria.getNumeroVaos(), geometria.getComprimentoVao(), aplicacao.getSobrecarga(), variante)) {
            resultado.add(produto);

            System.out.println(variante.getNome() + " pode ser usada.");
          } else {
            System.out.println(variante.getNome() + " não pode ser usada.");
          }

        }

      }

      List<ProdutoDTO> produtoDTOS = produtosToDTOs(resultado);
      return Response.status(Response.Status.OK).entity(produtoDTOS).build();

    } catch (Exception e) {
      log.info(e.getMessage());
    }
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }
}
