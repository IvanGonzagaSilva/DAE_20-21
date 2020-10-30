package ejbs;

import entities.Cliente;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton(name = "ConfigEJB")
public class configBean {

    @EJB
    clienteBean clienteBean;

    @PostConstruct
    public void populateDB(){
        System.out.println("Starting project...");

        System.out.println("Madeup clients:");
        clienteBean.create("joao", "joao123");
        clienteBean.create("ivan", "ivan123");
        clienteBean.create("pedrosa", "pedrosa123");
        System.out.println("Fim clientes");
    }
}
