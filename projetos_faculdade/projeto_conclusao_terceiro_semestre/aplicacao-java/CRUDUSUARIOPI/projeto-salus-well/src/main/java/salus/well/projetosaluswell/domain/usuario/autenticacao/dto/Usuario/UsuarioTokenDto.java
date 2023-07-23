package salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioTokenDto {
    private Long userId;
    private String email;
    private String senha;
    private String token;
}
