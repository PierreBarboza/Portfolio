package salus.well.projetosaluswell.domain.nutricionista;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;


public record NutricionistaCadastro(Long id, @NotBlank String nome,
                                    @NotBlank @Email @Column(unique = true) String email,
                                    @NotBlank String senha,
                                    @PositiveOrZero @Max(14) int avatar,
                                    String genero,
                                    @NotBlank String endereco,

                                    @NotBlank
                                    String crn,

                                    @NotBlank
                                    String telefone,
                                    boolean ativo
                                    ) {
}
