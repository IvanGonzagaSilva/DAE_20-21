package ws;


import dtos.SimulacaoDTO;
import dtos.VarianteDTO;
import ejbs.SimulacaoBean;
import ejbs.VarianteBean;
import entities.Variante;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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


    @POST
    @Path("/simulacao")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response simulacaoVarianteWS(SimulacaoDTO simulacaoDTO) {
        try {

            Variante variante = varianteBean.getVariante(simulacaoDTO.getCodVariante());

            if (variante == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            boolean simResult = simulacaoBean.simulaVariante(simulacaoDTO.getNb(), simulacaoDTO.getLVao(), simulacaoDTO.getQ(), variante);

            Response.Status status;
            if (simResult) {

                status = Response.Status.ACCEPTED;
            } else {
                status = Response.Status.NOT_ACCEPTABLE;
            }


            return Response.status(status).build();


        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
