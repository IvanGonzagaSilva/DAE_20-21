package ws;

import dtos.MaterialDTO;
import dtos.ProdutoDTO;
import ejbs.ProdutoBean;
import entities.Material;
import entities.Produto;

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
                produto.getId(),
                produto.getNome()
        );
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
