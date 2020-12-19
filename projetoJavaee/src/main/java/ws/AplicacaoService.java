package ws;


import dtos.AplicacaoDTO;
import ejbs.AplicacaoCoberturaBean;
import ejbs.AplicacaoFachadaBean;
import ejbs.AplicacaoGeralBean;
import entities.Aplicacao;
import entities.AplicacaoCobertura;
import entities.AplicacaoFachada;
import entities.AplicacaoGeral;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/aplicacao") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class AplicacaoService {

    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());

    @EJB
    private AplicacaoCoberturaBean aplicacaoCoberturaBean;
    @EJB
    private AplicacaoFachadaBean aplicacaoFachadaBean;
    @EJB
    private AplicacaoGeralBean aplicacaoGeralBean;


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAplicacaoWS(AplicacaoDTO aplicacaoDTO) {
        try {

            String tipo = aplicacaoDTO.getTipo();

            Aplicacao aplicacao;

            switch (tipo) {
                case "Geral": {
                    aplicacao = aplicacaoGeralBean.create(
                            aplicacaoDTO.getAngulo(),
                            aplicacaoDTO.getCargaPermanente(),
                            aplicacaoDTO.getSobrecarga(),
                            aplicacaoDTO.getCategoriaSobrecarga(),
                            aplicacaoDTO.getNeve(),
                            aplicacaoDTO.getVentoPressao(),
                            aplicacaoDTO.getVentoSucao()
                    );
                    return Response.status(Response.Status.CREATED).entity(aplicacaoGeralToDTO((AplicacaoGeral) aplicacao)).build();
                }
                case "Cobertura": {
                    aplicacao = aplicacaoCoberturaBean.create(
                            aplicacaoDTO.getAngulo(),
                            aplicacaoDTO.getCargaPermanente(),
                            aplicacaoDTO.getSobrecarga(),
                            aplicacaoDTO.getNeve(),
                            aplicacaoDTO.getVentoPressao(),
                            aplicacaoDTO.getVentoSucao()
                    );
                    return Response.status(Response.Status.CREATED).entity(aplicacaoCoberturaToDTO((AplicacaoCobertura) aplicacao)).build();
                }
                case "Fachada": {
                    aplicacao = aplicacaoFachadaBean.create(
                            aplicacaoDTO.getCargaPermanente(),
                            aplicacaoDTO.getVentoPressao(),
                            aplicacaoDTO.getVentoSucao()
                    );
                    return Response.status(Response.Status.CREATED).entity(aplicacaoFachadaToDTO((AplicacaoFachada) aplicacao)).build();
                }
            }


            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
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

    private AplicacaoDTO aplicacaoFachadaToDTO(AplicacaoFachada aplicacao) {
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

}
