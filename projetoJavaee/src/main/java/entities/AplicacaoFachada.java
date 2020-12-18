package entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
        @NamedQuery(
                name = "getAllAplicacoesFachadas",
                query = "SELECT af FROM AplicacaoFachada af ORDER BY af.id" // JPQL
        )
})

@Entity
public class AplicacaoFachada extends Aplicacao {

    public AplicacaoFachada() {
    }

    public AplicacaoFachada(int id, int cargaPermanente, int ventoPressao, int ventoSucao) {
        super(id, cargaPermanente, ventoPressao, ventoSucao, "Fachada");
    }
}
