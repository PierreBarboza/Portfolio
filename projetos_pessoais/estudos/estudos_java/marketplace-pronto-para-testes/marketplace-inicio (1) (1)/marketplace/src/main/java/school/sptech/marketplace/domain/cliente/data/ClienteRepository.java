package school.sptech.marketplace.domain.cliente.data;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.marketplace.domain.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
