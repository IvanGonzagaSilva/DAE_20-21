package ejbs;

import entities.*;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

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

  private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

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

    try {
      System.out.println("Starting project...");

      //fazer os materiais
      System.out.println("materiais!");
      Material material_chapa = materialBean.create(TipoDeMaterial.chapas);
      Material material_laje = materialBean.create(TipoDeMaterial.lajes);
      Material material_painel = materialBean.create(TipoDeMaterial.paineis);
      Material material_perfi = materialBean.create(TipoDeMaterial.perfis);
      System.out.println("fim materiais!");

      System.out.println("projetista!");
      projetistaBean.create("projetista1", "projetista@email.com", "projetista", "9143131615", "123abc");
      System.out.println("pessoa contacto!");
      pessoaDeContactoBean.create("pc1", "pc1@mail.com", "pessoadecontacto1", "914313616", "123abc");
      System.out.println("cliente!");
      clienteBean.create("cliente1", "pc1", "rua xpto", "clientex1@mail.com", "clientex", "123abc", "914313617");
      clienteBean.create("cliente2", "pc1", "rua xpto", "clientex1@mail.com", "clientex2", "123abc", "914313618");
      clienteBean.create("cliente3", "pc1", "rua xpto", "clientex1@mail.com", "clientex3", "123abc", "914313619");
      clienteBean.create("cliente4", "pc1", "rua xpto", "clientex1@mail.com", "clientex4", "123abc", "914313620");
      System.out.println("projeto!");
      Projeto projeto1 = projetoBean.create("projeto1", "projetista1");
      System.out.println("enroll cliente no projeto");
      projetoBean.enrollCliente(projeto1.getId(), "clientex");
      projetoBean.enrollCliente(projeto1.getId(), "clientex2");
      projetoBean.enrollCliente(projeto1.getId(), "clientex3");
      //fazer bean para estruturas e adicionar estrutura ao projeto.

      //CODIGO DO PROFESSOR
      System.out.println("####### A criar produtos...");
      produtoBean.create("Section C 220 BF");
      produtoBean.create("Section Z 220 BF");
      System.out.println("####### A criar variantes...");

      //PODE LER-SE OS VALORES DOS PRODUTOS/VARIANTES DE EXCELS OU CSVs (ver excels fornecidos)
      //Exemplo básico de adição de variantes "à mão"
      Variante variante1 = varianteBean.create("Section C 220 BF", "C 120/50/21 x 1.5", 13846, 13846, 375, 220000);
      Variante variante2 = varianteBean.create("Section C 220 BF", "C 120/60/13 x 2.0", 18738, 18738, 500, 220000);

      //PODE LER-SE OS VALORES mcr_p E mcr_n A PARTIR DE UM EXCEL OU CSV (ver excels fornecidos para os produtos Perfil C e Z, que têm os valores mcr)
      //Exemplo básico de adição de valores mcr "à mão"
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

      //CODIGO DO PROFESSOR.

      System.out.println("enrolling projetos nos projetistas...");
      //Enroll projeto no projetista
      projetistaBean.create("joaofrf", "joaofrf@mail.com", "joaofrf", "914123123", "123321abc");


      /*---------------------------------------------------------------------*/

      System.out.println("creating familias...");
      //Familias
      Familia fam1 = familiaBean.create("Todas");
      Familia fam2 = familiaBean.create("Ω");
      familiaBean.create("C");
      familiaBean.create("Z");

      System.out.println("creating geometrias...");
      //Geometrias
      Geometria geometria = geometriaBean.create(2, 3, 4);
      Geometria geometria1 = geometriaBean.create(3, 4, 6);
      geometriaBean.create(9, 6, 3);
      geometriaBean.create(4, 4, 4);

      geometriaBean.addFamiliaToGeometria(geometria.getId(), fam1.getNome());
      geometriaBean.addFamiliaToGeometria(geometria1.getId(), fam2.getNome());

      System.out.println("creating aplicações...");
      //Aplicações
      AplicacaoFachada aplicacaoFachada = aplicacaoFachadaBean.create(1, 1, 1);
      AplicacaoCobertura aplicacaoCobertura = aplicacaoCoberturaBean.create(2, 2, 2, 2, 2, 2);
      AplicacaoGeral aplicacaoGeral = aplicacaoGeralBean.create(3, 3, 3, 3, 3, 3, 3);

      System.out.println("creating parametros de calculo...");
      //Parametros de calculo
      ParametrosCalculo param1 = parametrosCalculoBean.create(true, "Frequente", 400);
      ParametrosCalculo param2 = parametrosCalculoBean.create(false, 23, false, "Quase permanente", 400);
      ParametrosCalculo param3 = parametrosCalculoBean.create(false, 12, true, 3, 4, "Caracteristica", 400);

      System.out.println("creating produtos...");
      //Produtos
      produtoBean.create("a");
      produtoBean.create("O");
      produtoBean.create("C");
      produtoBean.create("Z");

      System.out.println("creating estruturas...");
      //Estruturas
      Estrutura estrutura = estruturaBean.create("cobertura", geometria.getId(), aplicacaoCobertura.getId(), param1.getId());
      Estrutura estrutura1 = estruturaBean.create("fachada", geometria1.getId(), aplicacaoFachada.getId(), param2.getId());
      Estrutura estrutura2 = estruturaBean.create("geral", geometria.getId(), aplicacaoGeral.getId(), param3.getId());


      System.out.println("associating estruturas and produtos...");
      estruturaBean.addVariante(estrutura.getId(), variante1.getCodigo());
      estruturaBean.addVariante(estrutura1.getId(), variante2.getCodigo());
      estruturaBean.addVariante(estrutura2.getId(), variante1.getCodigo());

      System.out.println("estrutura");
      estruturaBean.addMaterial(estrutura.getId(), material_laje.getId());

      projetoBean.addEstrutura(projeto1.getId(), estrutura.getId());

      System.out.println("variante");

      Variante variante = varianteBean.create("a", "a 1.0", 12345, 12345, 400, 400500);

      variante.addMcr_p(3.0, 393.1408237);
      variante.addMcr_p(4.0, 241.9157907);
      variante.addMcr_p(5.0, 169.7815504);
      variante.addMcr_p(6.0, 129.3561949);
      variante.addMcr_p(7.0, 104.0782202);
      variante.addMcr_p(8.0, 86.9803928);
      variante.addMcr_p(9.0, 74.71876195);
      variante.addMcr_p(10.0, 65.52224563);
      variante.addMcr_p(11.0, 58.37786338);
      variante.addMcr_p(12.0, 52.65428332);

      variante.setMcr_n((LinkedHashMap<Double, Double>) variante2.getMcr_p().clone());

      System.out.println("simulacao joniiii");


      //EXEMPLO DA SIMULAÇÃO PARA DUAS VARIANTES DO PERFIL C, E PARA UMA ESTRUTURA DE 3 vãos (nb) de 3m cada (LVao) E SOBRECARGA 500000 (q)
      if (simulacaoBean.simulaVariante(geometria.getNumeroVaos(), geometria.getComprimentoVao(), aplicacaoGeral.getSobrecarga(), variante)) {
        System.out.println(variante.getNome() + " pode ser usada.");
      } else {
        System.out.println(variante.getNome() + " não pode ser usada.");
      }





      System.out.println("Config ended with success...");
    } catch (Exception e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
  }
}
