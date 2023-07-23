package salus.well.projetosaluswell.domain.usuario.autenticacao.dto.Nutricionista;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutricionistaTokenDto {
    private Long userId;
    private String email;
    private String senha;
    private String token;
}
