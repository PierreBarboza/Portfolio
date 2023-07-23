package salus.well.projetosaluswell.domain.comorbidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Long> {
    @Query("SELECT c FROM Comorbidade c JOIN c.cliente Cliente where cliente.email = :email ")
    Optional<Comorbidade> buscaComorbidadePorEmail(@Param("email") String email);

    @Query("SELECT c FROM Comorbidade c JOIN c.cliente Cliente where cliente.nutricionista.id = null")
    List<Comorbidade> buscaTodosSemVinculo();

    @Query("SELECT c FROM Comorbidade c JOIN c.cliente Cliente where cliente.nutricionista.id = :idNutricionista")
    List<Comorbidade> buscaTodosDeUmNutricionista(@Param("idNutricionista") Long id);

    @Query("SELECT c FROM Comorbidade c JOIN c.cliente Cliente where cliente.nome = :nomeCliente")
    List<Comorbidade> buscaComorbidadePorNomeDoCliente(@Param("nomeCliente") String nomeCliente);
}
