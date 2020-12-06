package ws;

import dtos.ClienteDTO;
import dtos.MaterialDTO;
import dtos.ProjetoDTO;
import ejbs.MaterialBean;
import ejbs.ProjetoBean;
import entities.Cliente;
import entities.Material;
import entities.Projeto;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/materiais") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class MateriaisService {
    @EJB
    private MaterialBean materialBean;

    private MaterialDTO toDTO(Material material){
        return new MaterialDTO(
                material.getTipoDeMaterial()
        );
    }

    private List<MaterialDTO> materialToDTOs(List<Material> material){
        return material.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/students/”
    public List<MaterialDTO> getAllMateriaisWS() {
        return materialToDTOs(materialBean.getAllMateriais());
    }

}
