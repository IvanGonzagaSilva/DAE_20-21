package ejbs;

import entities.Geometria;
import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;
import exceptions.MyEntityNotFoundException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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
    /*------------------------------------*/
    @EJB
    FamiliaBean familiaBean;
    @EJB
    GeometriaBean geometriaBean;
    @EJB
    MaterialBean materialBean;
    @EJB
    EstruturaBean estruturaBean;

    @PostConstruct
    public void populateDB()
            throws MyEntityExistsException, MyConstraintViolationException, MyEntityNotFoundException {
        
        System.out.println("Starting project...");

        System.out.println("creating projetistas...");
        //Projetistas
        projetistaBean.create("joaofrf", "joao@joao.joao", "joao ferreira", "914313615");

        System.out.println("creating pcs...");
        //Pessoa de Contacto (PC)
        pessoaDeContactoBean.create("ivan", "ivan@ivan.ivan", "ivan silva", "914313616");

        System.out.println("creating clientes...");
        //Clientes
        clienteBean.create("Empresa do Pedrosa", "ivan", "rua nao sei de onde", "empresa@empresa.empresa");

        System.out.println("creating projetos...");
        //Projetos
        projetoBean.create(1, "o grande projeto", "empresa@empresa.empresa");
        projetoBean.create(2, "o grande projeto 2", "empresa@empresa.empresa");

        System.out.println("enrolling projetos nos projetistas...");
        //Enroll projeto no projetista
        projetistaBean.enrollInProject("joaofrf",1);
        projetistaBean.enrollInProject("joaofrf",2);

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

        System.out.println("add familias to geometrias...");
        //Add Familias to Geometrias
        geometriaBean.addFamiliaToGeometria(1, 2);
        geometriaBean.addFamiliaToGeometria(2, 3);
        geometriaBean.addFamiliaToGeometria(3, 1);
        geometriaBean.addFamiliaToGeometria(4, 4);

        System.out.println("creating materiais...");
        //Materiais
        materialBean.create(1, "Perfil");
        materialBean.create(2, "Chapa");
        materialBean.create(3, "Laje");
        materialBean.create(4, "Painel");

        System.out.println("creating estruturas...");
        //Estruturas
        estruturaBean.create(1, "cobertura", 1, 1);
        estruturaBean.create(1, "fachada", 2, 2);
        estruturaBean.create(1, "geral", 3, 3);
    }
}
