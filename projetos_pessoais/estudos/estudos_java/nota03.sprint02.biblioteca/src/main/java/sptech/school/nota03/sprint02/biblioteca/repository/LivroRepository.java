package sptech.school.nota03.sprint02.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nota03.sprint02.biblioteca.classe.Livro;

import java.time.LocalDate;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findBynomeEscritorIgnoreCase(String nome);


    List<Livro> findBydtaLancamentoLessThanEqual(LocalDate data);



    List<Livro> findByqtdPagLessThanEqual(int qtd);


    Livro findBynomeCapaIgnoreCase(String nome);

}

