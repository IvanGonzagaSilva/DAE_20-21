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

            varianteBean.create(varianteDTO.getCodigo(), varianteDTO.getNomeProduto(), varianteDTO.getNome(), varianteDTO.getWeff_p(), varianteDTO.getWeff_n(), varianteDTO.getAr(), varianteDTO.getSigmaC());

            Variante variante = varianteBean.getVariante(varianteDTO.getCodigo());

            if (variante == null) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }


            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
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
