package sptech.school.sprint03nota02.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.school.sprint03nota02.domain.Escritor;
import sptech.school.sprint03nota02.domain.Livro;
import sptech.school.sprint03nota02.dto.LivroAtualizarDto;
import sptech.school.sprint03nota02.dto.LivroNovoDto;
import sptech.school.sprint03nota02.dto.LivroResumido;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {


    @Query("SELECT new sptech.school.sprint03nota02.dto.LivroResumido(l.nomeLivro, l.escritor.nomeEscritor) FROM Livro l")
    List<LivroResumido> listarLivrosResumidos();


    @Modifying
    @Transactional
    @Query("INSERT INTO Livro (nomeLivro, escritor, bestSeller) VALUES (:nomeLivro, :escritor, :bestSeller)")
    void addLivro(String nomeLivro, Escritor escritor, Boolean bestSeller);

    @Transactional
    @Modifying
    @Query("UPDATE Livro l SET l.nomeLivro=:nomeLivro, l.bestSeller=:bestSeller WHERE l.id=:id")
    void atualizarLivro(long id, String nomeLivro, Boolean bestSeller);

    List<Livro> findByBestSellerTrue();


    @Transactional
    @Modifying
    @Query("DELETE FROM Livro l WHERE l.id = :id")
    void removerPorId(long id);

}
