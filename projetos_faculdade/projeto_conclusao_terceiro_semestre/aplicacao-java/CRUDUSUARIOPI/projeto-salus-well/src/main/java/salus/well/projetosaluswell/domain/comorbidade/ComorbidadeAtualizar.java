package salus.well.projetosaluswell.domain.comorbidade;


import jakarta.validation.constraints.NotNull;

public record ComorbidadeAtualizar(
        @NotNull
        Long id,
        boolean diabete,
        boolean colesterol,
        boolean hipertensao) {

}
