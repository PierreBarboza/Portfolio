package school.sptech.marketplace.domain.produto.data;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.marketplace.domain.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
