package salus.well.projetosaluswell.domain.nutricionista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ProblemDetail;

import java.util.List;
import java.util.Optional;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    List<Nutricionista> findByAtivoTrue();
    Optional<Nutricionista> findByEmailIgnoreCase(String email);
    Optional<Nutricionista> findByEmail(String emailNutricionista);
    List<Nutricionista> findByNomeIgnoreCase(String nome);




//    Nutricionista findByAtivoTrueId(Long id);

}
