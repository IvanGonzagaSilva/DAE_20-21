package ws;


import dtos.ParametrosCalculoDTO;
import ejbs.ParametrosCalculoBean;
import entities.ParametrosCalculo;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/params") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class ParametrosCalculoService {
    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());

    @EJB
    private ParametrosCalculoBean parametrosCalculoBean;


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGeometriaWS(ParametrosCalculoDTO parametrosCalculoDTO) {
        try {
            ParametrosCalculo params;

            if (parametrosCalculoDTO.isContraventamentoTotal()) {
                params = parametrosCalculoBean.create(true, parametrosCalculoDTO.getVerificacaoDeformacao(), parametrosCalculoDTO.getLimiteDeformacao());
            } else {
                if (parametrosCalculoDTO.isContribuicaoChapaRevestimento()) {
                    params = parametrosCalculoBean.create(false, parametrosCalculoDTO.getNumeroContraventamentosLaterais(),
                            true, parametrosCalculoDTO.getNumeroFixacoes(), parametrosCalculoDTO.getInerciaChapaRevestimento(),
                            parametrosCalculoDTO.getVerificacaoDeformacao(), parametrosCalculoDTO.getLimiteDeformacao());
                } else {
                    params = parametrosCalculoBean.create(false, parametrosCalculoDTO.getNumeroContraventamentosLaterais(),
                      false,
                            parametrosCalculoDTO.getVerificacaoDeformacao(), parametrosCalculoDTO.getLimiteDeformacao());
                }
            }

            return Response.status(Response.Status.CREATED).entity(toDTO(params)).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    private ParametrosCalculoDTO toDTO(ParametrosCalculo parametrosCalculo) {
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
}
