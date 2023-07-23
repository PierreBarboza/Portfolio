package salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginDto {
    private String email;
    private String senha;
}
