package salus.well.projetosaluswell.domain.usuario.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private String nome;
    private String email;
    private String senha;
    private int avatar;
    private String genero;
    private String endereco;
    private boolean comorbidade;

}
