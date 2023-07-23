package salus.well.projetosaluswell.domain.alimento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    List<Alimento> findByDiabeteTrue();
    List<Alimento> findByColesterolTrue();
    List<Alimento> findByHipertensaoTrue();
    List<Alimento> findByTipoIgnoreCase(String tipo);
    Optional<Alimento> findByNomeIgnoreCase(String nomeAlimento);

    List<Alimento> findByIdIn(List<Long> ids);


    @Modifying
    @Transactional
    @Query("update Alimento a set a.relatorioExcel = ?2 where a.id = ?1")
    void setRelatorio(Long id, byte[] relatorioExcel);

    @Query("select a.relatorioExcel from Alimento a where a.id = ?1")
    byte[] getRelatorio(Long id);

}
