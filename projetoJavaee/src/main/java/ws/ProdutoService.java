package ws;

import dtos.MaterialDTO;
import dtos.ProdutoDTO;
import dtos.VarianteDTO;
import ejbs.ProdutoBean;
import entities.Material;
import entities.Produto;
import entities.Variante;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/produto")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})

public class ProdutoService {

    @EJB
    private ProdutoBean produtoBean;

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getNome(),
                variantesToDTOs(produto.getVariantes())
        );
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



    private List<VarianteDTO> variantesToDTOs(List<Variante> variantes){
        return variantes.stream().map(this::varianteToDTO).collect(Collectors.toList());
    }

    private List<ProdutoDTO> produtoToDTOs(List<Produto> produtos) {
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<ProdutoDTO> getAllProdutosWS() {
        List<ProdutoDTO> produtoDTOS = produtoToDTOs(produtoBean.getAllProdutos());
        return produtoDTOS;
    }
}
