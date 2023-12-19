package school.sptech.marketplace.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.marketplace.dominio.AvaliacaoCompradorProduto;

public interface AvaliacaoCompradorProdutoRepository extends JpaRepository<AvaliacaoCompradorProduto, Long> {
}
