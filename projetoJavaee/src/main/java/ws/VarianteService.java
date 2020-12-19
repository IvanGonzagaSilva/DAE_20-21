package ws;


import dtos.*;
import ejbs.*;
import entities.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/variante") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class VarianteService {

    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());

    @EJB
    VarianteBean varianteBean;
    @EJB
    SimulacaoBean simulacaoBean;
    @EJB
    GeometriaBean geometriaBean;
    @EJB
    AplicacaoCoberturaBean aplicacaoCoberturaBean;
    @EJB
    AplicacaoGeralBean aplicacaoGeralBean;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVarianteWS(VarianteDTO varianteDTO) {
        try {

          Variante variante = varianteBean.create(varianteDTO.getNomeProduto(), varianteDTO.getNome(), varianteDTO.getWeff_p(), varianteDTO.getWeff_n(), varianteDTO.getAr(), varianteDTO.getSigmaC());


          if (variante == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }


            return Response.status(Response.Status.CREATED).entity(varianteToDTO(variante)).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

  @GET
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response getAllVarianteWS() {
    try {

      List<VarianteDTO> varianteDTOS = variantesToDTOs(varianteBean.getAllVariantes());
      return Response.status(Response.Status.OK).entity(varianteDTOS).build();
    } catch (Exception e) {
      log.info(e.getMessage());
    }
    return Response.status(Response.Status.CONFLICT).build();
  }

  private List<VarianteDTO> variantesToDTOs(List<Variante> variantes) {
    return variantes.stream().map(this::varianteToDTO).collect(Collectors.toList());
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

}
