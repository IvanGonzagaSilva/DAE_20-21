package ejbs;

import entities.Pessoa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AuthBean {

    @PersistenceContext

    EntityManager em;

    public Pessoa authenticate(final String username, final String password) throws
            Exception {
        Pessoa user = em.find(Pessoa.class, username);
        if (user != null &&
                user.getPassword().equals(Pessoa.hashPassword(password))) {
            return user;
        }
        throw new Exception("Failed logging in with username '" + username + "': unknown username or wrong password");
    }
}
