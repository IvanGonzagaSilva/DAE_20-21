package ws;

import dtos.FamiliaDTO;
import dtos.GeometriaDTO;
import ejbs.GeometriaBean;
import entities.Familia;
import entities.Geometria;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/geometria") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class GeometriaService {

    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());

    @EJB
    private GeometriaBean geometriaBean;

    private GeometriaDTO toDTO(Geometria geometria) {
        return new GeometriaDTO(
                geometria.getId(),
                geometria.getNumeroVaos(),
                geometria.getComprimentoVao(),
                geometria.getEspacamentoVigas(),
                familiasToDTOs(geometria.getFamilias())
        );
    }

    private FamiliaDTO familiaToDTO(Familia familia) {
        return new FamiliaDTO(familia.getNome());
    }

    private List<FamiliaDTO> familiasToDTOs(List<Familia> familia) {
        return familia.stream().map(this::familiaToDTO).collect(Collectors.toList());
    }

    private List<GeometriaDTO> toDTOs(List<Geometria> geometrias) {
        return geometrias.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGeometriaWS(GeometriaDTO geometriaDTO) {
        try {

            Geometria geometria = geometriaBean.create(geometriaDTO.getNumeroVaos(), geometriaDTO.getComprimentoVao(), geometriaDTO.getEspacamentoVigas());

            return Response.status(Response.Status.CREATED).entity(toDTO(geometria)).build();

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
