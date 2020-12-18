package ws;

import dtos.*;
import ejbs.EstruturaBean;
import ejbs.ProjetoBean;
import entities.*;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/estrutura") // relative url web path for this service
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”

public class EstruturaService {

    @EJB
    private EstruturaBean estruturaBean;

    private EstruturaDTO toDTO(Estrutura estrutura) {

        AplicacaoDTO aplicacao = null;

        switch (estrutura.getAplicacao().getTipo()) {
            case "Geral": aplicacao = aplicacaoGeralToDTO((AplicacaoGeral) estrutura.getAplicacao());
                break;
            case "Cobertura": aplicacao = aplicacaoCoberturaToDTO((AplicacaoCobertura)estrutura.getAplicacao());
                break;
            case "Fachada":aplicacao = aplicacaoToDTO((AplicacaoFachada)estrutura.getAplicacao());
                break;
            default: //TODO throw new....
        }


        return new EstruturaDTO(
                estrutura.getId(),
                estrutura.getNome(),
                geometriaToDTO(estrutura.getGeometria()),
                aplicacao,
                parametrosCalculoToDTO(estrutura.getParametrosCalculo()),
                materialToDTO(estrutura.getMaterial()),
                produtosToDTOs(estrutura.getProdutos())
        );
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


    private GeometriaDTO geometriaToDTO(Geometria geometria) {
        return new GeometriaDTO(
                geometria.getId(),
                geometria.getNumeroVaos(),
                geometria.getComprimentoVao(),
                geometria.getEspacamentoVigas(),
                familiaToDTOs(geometria.getFamilias())
        );
    }

    private List<FamiliaDTO> familiaToDTOs(List<Familia> familias) {
        return familias.stream().map(this::familiaToDTO).collect(Collectors.toList());
    }

    private FamiliaDTO familiaToDTO(Familia familia) {
        return new FamiliaDTO(
                familia.getId(),
                familia.getNome()
        );
    }

    //Verificar com o prof, porque pode ser varios tipos de aplicacao
    private AplicacaoDTO aplicacaoToDTO(Aplicacao aplicacao) {
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

    private ParametrosCalculoDTO parametrosCalculoToDTO(ParametrosCalculo parametrosCalculo) {
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

    private MaterialDTO materialToDTO(Material material) {
        return new MaterialDTO(
                material.getId(),
                material.getNome()
        );
    }

    private List<ProdutoDTO> produtosToDTOs(List<Produto> produtos) {
        return produtos.stream().map(this::produtoToDTO).collect(Collectors.toList());
    }

    private ProdutoDTO produtoToDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome()
        );
    }

    private List<EstruturaDTO> estruturaToDTOs(List<Estrutura> estruturas) {
        return estruturas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/")
    public List<EstruturaDTO> getAllEstruturasWS() {
        List<EstruturaDTO> estruturaDTOS = estruturaToDTOs(estruturaBean.getAllEstruturas());
        return estruturaDTOS;
    }
}
