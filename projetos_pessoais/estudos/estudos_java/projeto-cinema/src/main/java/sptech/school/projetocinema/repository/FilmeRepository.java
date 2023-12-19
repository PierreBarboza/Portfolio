package sptech.school.projetocinema.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.school.projetocinema.domain.Filme;
import sptech.school.projetocinema.dto.FilmeResumoRespostaDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    @Query("SELECT COUNT(f) FROM Filme f WHERE f.indicacaoOscar")
    Integer contagemSomenteIndicados();

    @Query("SELECT AVG(custoProducao) FROM Filme f")
    double mediaCustoProducao();

    @Query("SELECT COUNT(f) FROM Filme f WHERE f.diretor.nome iLike %:nome% AND f.indicacaoOscar")
    int contagemIndicadosPorDiretor(String nome);

    @Query("SELECT new sptech.school.projetocinema.dto.FilmeResumoRespostaDto(f.nome, f.diretor.nome) FROM Filme f")
    List<FilmeResumoRespostaDto> listarFilmesResumido();

    @Transactional
    @Modifying
    @Query("DELETE FROM Filme f WHERE f.id = :id")
    void removerPorId(long id);

    @Transactional
    @Modifying
    @Query("UPDATE Filme f SET f.nome=:nome WHERE f.id=:id")
    Filme atualizarPorId(long id, String nome);

    Optional<Filme> findByNome(String nome);

    List<Filme> findByDiretorNomeContainsIgnoreCase(String nome);

    List<Filme> findByDataLancamentoLessThanEqual(LocalDate data);

    List<Filme> findByIndicacaoOscarTrue();

    int countByIndicacaoOscarFalse();

    List<Filme> findByCustoProducaoGreaterThan(double custo);

    List<Filme> findTop5ByOrderByIdDesc();
}
