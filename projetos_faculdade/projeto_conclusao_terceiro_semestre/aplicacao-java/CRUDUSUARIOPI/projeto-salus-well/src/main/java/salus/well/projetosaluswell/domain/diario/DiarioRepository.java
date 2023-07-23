package salus.well.projetosaluswell.domain.diario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import salus.well.projetosaluswell.domain.alimento.Alimento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiarioRepository extends JpaRepository<DiarioAlimentar, Long> {
    //Page<DiarioAlimentar> findByNutricionista_Email(String email, Pageable page);
    //Page<DiarioAlimentar> findByAlimento_Nome(String nome, Pageable page);

    // select dos alimentos dando where no id do usuario e no dia de consumir
    @Query("SELECT da FROM DiarioAlimentar da WHERE da.cliente.id = :usuarioId AND da.dataConsumir = :dataConsumo")
    List<DiarioAlimentar> alimentosConsumirCliente(@Param("usuarioId") Long usuarioId, @Param("dataConsumo")LocalDate dataConsumo);

    @Query("SELECT da FROM DiarioAlimentar da WHERE da.consumido = false AND da.cliente.id = :usuarioId AND da.id = :diarioId")
    Optional<DiarioAlimentar> diarioNaoConsumidoPorId(@Param("usuarioId") Long usuarioId, @Param("diarioId") Long diarioId);

    @Query("SELECT da FROM DiarioAlimentar da WHERE da.cliente.id = :clienteId AND (da.consumido = true OR da.dataConsumir < CURRENT_DATE)")
    List<DiarioAlimentar> diarioConsumidoHistorico(@Param("clienteId") Long clienteId);

    List<DiarioAlimentar> findByConsumidoTrue();

   /* @Query("SELECT d.alimento FROM DiarioAlimentar d WHERE d.alimento.id IN :ids")
    List<Alimento> findAlimentosByNomeInDiarios(List<Long> ids);
*/
}
