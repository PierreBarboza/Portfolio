package school.sptech.marketplace.domain.produto.avaliacao.data;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.marketplace.domain.produto.avaliacao.AvaliacaoClienteProduto;

public interface AvaliacaoClienteProdutoRepository extends JpaRepository<AvaliacaoClienteProduto, Long> {
}
