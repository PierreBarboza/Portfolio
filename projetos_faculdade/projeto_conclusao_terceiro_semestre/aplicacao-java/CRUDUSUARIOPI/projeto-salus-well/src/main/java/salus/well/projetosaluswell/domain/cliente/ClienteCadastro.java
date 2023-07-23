package salus.well.projetosaluswell.domain.cliente;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;


public record ClienteCadastro(

                                @NotBlank String nome,
                              @NotBlank @Email @Column(unique = true) String email,
                              @NotBlank String senha,
                              @PositiveOrZero @Max(14) int avatar,
                              String genero,
                              @NotBlank String endereco,
                              boolean comorbidade,

                              @NotBlank
                                String telefone,
                              boolean ativo) {
}
