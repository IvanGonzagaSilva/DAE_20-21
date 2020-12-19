package ws;

import dtos.FamiliaDTO;
import dtos.MaterialDTO;
import ejbs.FamiliaBean;
import ejbs.MaterialBean;
import entities.Familia;
import entities.Material;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;


@Path("/familia") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class FamiliaService {

  @EJB
  private FamiliaBean familiaBean;

  private FamiliaDTO toDTO(Familia familia){
    return new FamiliaDTO(familia.getNome());
  }

  private List<FamiliaDTO> toDTOs(List<Familia> familia){
    return familia.stream().map(this::toDTO).collect(Collectors.toList());
  }

  @GET // means: to call this endpoint, we need to use the HTTP GET method
  @Path("/") // means: the relative url path is “/api/students/”
  public List<FamiliaDTO> getAllFamiliasWS() {
    return toDTOs(familiaBean.getAllFamilias());
  }

}
