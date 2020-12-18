package ejbs;

import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;
import entities.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton(name = "ConfigEJB")
public class ConfigBean {

    @EJB
    ClienteBean clienteBean;
    @EJB
    PessoaDeContactoBean pessoaDeContactoBean;
    @EJB
    ProjetistaBean projetistaBean;
    @EJB
    ProjetoBean projetoBean;
    @EJB
    FamiliaBean familiaBean;
    @EJB
    GeometriaBean geometriaBean;
    @EJB
    AplicacaoFachadaBean aplicacaoFachadaBean;
    @EJB
    AplicacaoCoberturaBean aplicacaoCoberturaBean;
    @EJB
    AplicacaoGeralBean aplicacaoGeralBean;
    @EJB
    ParametrosCalculoBean parametrosCalculoBean;
    @EJB
    ProdutoBean produtoBean;
    @EJB
    VarianteBean varianteBean;
    @EJB
    SimulacaoBean simulacaoBean;
    @EJB
    MaterialBean materialBean;
    @EJB
    EstruturaBean estruturaBean;

    @PostConstruct
    public void populateDB()
            throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {

        System.out.println("Starting project...");

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @PostConstruct
    public void populateDB(){
        try {
            System.out.println("Starting project...");

            //fazer os materiais
            Material material_chapa = materialBean.create(TipoDeMaterial.chapas);
            Material material_laje = materialBean.create(TipoDeMaterial.lajes);
            Material material_painel = materialBean.create(TipoDeMaterial.paineis);
            Material material_perfi = materialBean.create(TipoDeMaterial.perfis);

            projetistaBean.create("projetista1", "projetista@email.com", "projetista", "9143131615", "123abc");
            pessoaDeContactoBean.create("pc1", "pc1@mail.com", "pessoadecontacto1", "914313616", "123abc");
            clienteBean.create("cliente", "pc1", "rua xpto", "clientex@mail.com");
            Projeto projeto1 = projetoBean.create("projeto1", "projetista1");
            projetoBean.enrollCliente(projeto1.getId(), "clientex@mail.com");
            Estrutura estrutura1 = estruturaBean.create();
            estrutura1.addMaterial(material_chapa);
            estrutura1.addMaterial(material_laje);

            projeto1.addEstruturas(estrutura1);
            //fazer bean para estruturas e adicionar estrutura ao projeto.

            //CODIGO DO PROFESSOR
            System.out.println("####### A criar produtos...");
            produtoBean.create("Section C 220 BF");
            produtoBean.create("Section Z 220 BF");
            System.out.println("####### A criar variantes...");

            //PODE LER-SE OS VALORES DOS PRODUTOS/VARIANTES DE EXCELS OU CSVs (ver excels fornecidos)
            //Exemplo básico de adição de variantes "à mão"
            varianteBean.create(1, "Section C 220 BF", "C 120/50/21 x 1.5", 13846, 13846, 375, 220000);
            varianteBean.create(2, "Section C 220 BF", "C 120/60/13 x 2.0", 18738, 18738, 500, 220000);

            //PODE LER-SE OS VALORES mcr_p E mcr_n A PARTIR DE UM EXCEL OU CSV (ver excels fornecidos para os produtos Perfil C e Z, que têm os valores mcr)
            //Exemplo básico de adição de valores mcr "à mão"
            Variante variante1 = varianteBean.getVariante(1);
            variante1.addMcr_p(3.0, 243.2123113);
            variante1.addMcr_p(4.0, 145.238784);
            variante1.addMcr_p(5.0, 99.15039028);
            variante1.addMcr_p(6.0, 73.71351699);
            variante1.addMcr_p(7.0, 58.07716688);
            variante1.addMcr_p(8.0, 47.68885195);
            variante1.addMcr_p(9.0, 40.37070843);
            variante1.addMcr_p(10.0, 34.9747033);
            variante1.addMcr_p(11.0, 30.84866055);
            variante1.addMcr_p(12.0, 27.59984422);


            //Válido para variantes simétricas, em que os mcr_p são iguais aos mcr_n
            variante1.setMcr_n((LinkedHashMap<Double, Double>) variante1.getMcr_p().clone());

            Variante variante2 = varianteBean.getVariante(2);
            variante2.addMcr_p(3.0, 393.1408237);
            variante2.addMcr_p(4.0, 241.9157907);
            variante2.addMcr_p(5.0, 169.7815504);
            variante2.addMcr_p(6.0, 129.3561949);
            variante2.addMcr_p(7.0, 104.0782202);
            variante2.addMcr_p(8.0, 86.9803928);
            variante2.addMcr_p(9.0, 74.71876195);
            variante2.addMcr_p(10.0, 65.52224563);
            variante2.addMcr_p(11.0, 58.37786338);
            variante2.addMcr_p(12.0, 52.65428332);

            //Válido para variantes de geometria simétrica, em que os mcr_p são iguais aos mcr_n
            variante2.setMcr_n((LinkedHashMap<Double, Double>) variante2.getMcr_p().clone());

            System.out.println("####### FINISHED!!");

            //EXEMPLO DA SIMULAÇÃO PARA DUAS VARIANTES DO PERFIL C, E PARA UMA ESTRUTURA DE 3 vãos (nb) de 3m cada (LVao) E SOBRECARGA 500000 (q)
            if (simulacaoBean.simulaVariante(3, 3.0, 500000, variante1)) {
                System.out.println(variante1.getNome() + " pode ser usada.");
            } else {
                System.out.println(variante1.getNome() + " não pode ser usada.");
            }

            if (simulacaoBean.simulaVariante(3, 3.0, 500, variante2)) {
                System.out.println("A variante " + variante2.getNome() + " pode ser usada.");
            } else {
                System.out.println("A variante " + variante2.getNome() + " não pode ser usada.");
            }

        System.out.println("enrolling projetos nos projetistas...");
        //Enroll projeto no projetista
        projetistaBean.enrollInProject("joaofrf", 1);
        projetistaBean.enrollInProject("joaofrf", 2);

        /*---------------------------------------------------------------------*/

        System.out.println("creating familias...");
        //Familias
        familiaBean.create(1, "Todas");
        familiaBean.create(2, "O");
        familiaBean.create(3, "C");
        familiaBean.create(4, "Z");

        System.out.println("creating geometrias...");
        //Geometrias
        geometriaBean.create(1, 2, 3, 4);
        geometriaBean.create(2, 3, 4, 6);
        geometriaBean.create(3, 9, 6, 3);
        geometriaBean.create(4, 4, 4, 4);

        geometriaBean.addFamiliaToGeometria(1, 1);
        geometriaBean.addFamiliaToGeometria(2, 2);
        geometriaBean.addFamiliaToGeometria(3, 3);
        geometriaBean.addFamiliaToGeometria(4, 4);

        System.out.println("creating aplicações...");
        //Aplicações
        aplicacaoFachadaBean.create(1, 1, 1, 1);
        aplicacaoCoberturaBean.create(2, 2, 2, 2, 2, 2, 2);
        aplicacaoGeralBean.create(3, 3, 3, 3, 3, 3, 3, 3);

        System.out.println("creating parametros de calculo...");
        //Parametros de calculo
        parametrosCalculoBean.create(1, true, "Frequente", 400);
        parametrosCalculoBean.create(2, false, 23, false, "Quase permanente", 400);
        parametrosCalculoBean.create(3, false, 12, true, 3, 4, "Caracteristica", 400);

        System.out.println("creating materiais...");
        //Materiais
        materialBean.create(1, "Perfil");
        materialBean.create(2, "Chapa");
        materialBean.create(3, "Laje");
        materialBean.create(4, "Painel");

        System.out.println("creating produtos...");
        //Produtos
        produtoBean.create(1, "a");
        produtoBean.create(2, "O");
        produtoBean.create(3, "C");
        produtoBean.create(4, "Z");

        System.out.println("creating estruturas...");
        //Estruturas
        estruturaBean.create(1, "cobertura", 1, 1, 1, 1);
        estruturaBean.create(2, "fachada", 2, 2, 2, 2);
        estruturaBean.create(3, "geral", 3, 3, 3, 3);

            //CODIGO DO PROFESSOR.
        

        System.out.println("associating estruturas and produtos...");
        estruturaBean.addProduto(1, 1);
        estruturaBean.addProduto(2, 2);
        estruturaBean.addProduto(3, 3);

        }
        catch (Exception e){
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
