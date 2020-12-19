package ws;


import dtos.McrPDTO;
import dtos.SimulacaoDTO;
import dtos.VarianteDTO;
import ejbs.*;
import entities.Aplicacao;
import entities.AplicacaoGeral;
import entities.Geometria;
import entities.Variante;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

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
        return Response.status(Response.Status.UNAUTHORIZED).build();
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
