package sptech.school.sprint03nota02.repository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.school.sprint03nota02.domain.Escritor;
import sptech.school.sprint03nota02.domain.Livro;
import sptech.school.sprint03nota02.dto.EscritorResumidoDto;
import sptech.school.sprint03nota02.dto.LivroResumido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EscritorRepository extends JpaRepository<Escritor, Long> {

    @Query("SELECT new sptech.school.sprint03nota02.dto.EscritorResumidoDto(e.nomeEscritor) FROM Escritor e")
    List<EscritorResumidoDto> listarEscritorResumidos();

    Optional<Escritor> findByNomeEscritor(String nomeEscritor);

    List<Escritor> findByDataNascLessThanEqual(LocalDate dataNasc);

    @Transactional
    @Modifying
    @Query("DELETE FROM Escritor e WHERE e.id =:id")
    void removerEscritorPorId(long id);

}
