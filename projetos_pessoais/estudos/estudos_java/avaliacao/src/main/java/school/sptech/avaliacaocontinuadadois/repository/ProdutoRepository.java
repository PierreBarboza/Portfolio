package school.sptech.avaliacaocontinuadadois.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.avaliacaocontinuadadois.domain.Produto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    long count();

    long countByCategoriaIgnoreCase(String categoria);

    List<Produto> findByCategoriaIgnoreCase(String categoria);

    Produto findFirstByCategoriaIgnoreCaseOrderByPrecoDesc(String categoria);

    Produto findFirstByCategoriaIgnoreCaseOrderByPreco(String categoria);

    long countByCategoriaIgnoreCaseAndPromocaoTrue(String Categoria);

    List<Produto> findByDataCriacaoBetween(LocalDate dataInicial, LocalDate dataFinal);
}
