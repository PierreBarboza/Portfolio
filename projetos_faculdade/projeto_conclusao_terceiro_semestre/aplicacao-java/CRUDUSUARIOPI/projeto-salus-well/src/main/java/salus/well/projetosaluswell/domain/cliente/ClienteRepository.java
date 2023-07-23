package salus.well.projetosaluswell.domain.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByAtivoTrue();
    Optional<Cliente> findByEmailIgnoreCase(String email);
    Cliente findByEmail(String emailCliente);
    Cliente findByNomeIgnoreCase(String nome);

    List<Cliente> findByNutricionistaId(Long id);
    List<Cliente> findAllByNutricionistaId(Long id);


    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(:nome) AND c.ativo = true")
    List<Cliente> buscaPorNome(@Param("nome") String nome);

    @Query("SELECT c.nutricionista FROM Cliente c WHERE c.id = :clienteId")
    Optional<Nutricionista> findNutricionistaByClienteId(@Param("clienteId") Long clienteId);


    @Query("SELECT c FROM Cliente c WHERE c.nutricionista is NULL")
    List<Cliente> clientesSemVinculo();

    @Modifying
    @Transactional
    @Query("update Cliente c set c.relatorioTxt = ?2 where c.id = ?1")
    void setTxt(Long id, byte[] relatorioTxt);

    @Query("select c.relatorioTxt from Cliente c where c.id = ?1")
    byte[] getRelatorio(Long id);

//    Cliente findByAtivoTrueId(Long id);
}
