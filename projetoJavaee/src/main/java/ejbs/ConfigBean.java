package ejbs;

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

    @PostConstruct
    public void populateDB(){
        System.out.println("Starting project...");

        //Projetistas
        projetistaBean.create("joaofrf", "joao@joao.joao", "joao ferreira", "914313615");

        //Pessoa de Contacto (PC)
        pessoaDeContactoBean.create("ivan", "ivan@ivan.ivan", "ivan silva", "914313616");

        //Clientes
        clienteBean.create("Empresa do Pedrosa", "ivan", "rua nao sei de onde", "empresa@empresa.empresa");

        //Projetos
        projetoBean.create(1, "o grande projeto", "empresa@empresa.empresa");
        projetoBean.create(2, "o grande projeto 2", "empresa@empresa.empresa");

        //Enroll projeto no projetista
        projetistaBean.enrollInProject("joaofrf",1);
        projetistaBean.enrollInProject("joaofrf",2);

    }
}
