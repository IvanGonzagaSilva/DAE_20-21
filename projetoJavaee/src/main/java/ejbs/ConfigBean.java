package ejbs;

import exceptions.MyConstraintViolationException;
import exceptions.MyEntityExistsException;

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
    @EJB
    MaterialBean materialBean;
    @EJB
    EstruturaBean estruturaBean;

    @PostConstruct
    public void populateDB() throws MyEntityExistsException, MyConstraintViolationException {
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

        System.out.println("creating materiais...");
        //Materiais
        materialBean.create(1, "Perfil");
        materialBean.create(2, "Chapa");
        materialBean.create(3, "Laje");
        materialBean.create(4, "Painel");

        System.out.println("creating estruturas...");
        //Estruturas
        estruturaBean.create(1, "cobertura", 2, 3, 4, "z", 1);
    }
}
