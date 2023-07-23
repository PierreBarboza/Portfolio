package salus.well.projetosaluswell.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import salus.well.projetosaluswell.domain.cliente.Cliente;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);
}
