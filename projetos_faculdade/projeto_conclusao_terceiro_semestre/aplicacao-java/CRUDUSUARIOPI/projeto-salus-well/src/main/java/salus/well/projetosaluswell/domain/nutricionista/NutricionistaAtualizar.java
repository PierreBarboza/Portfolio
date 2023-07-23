package salus.well.projetosaluswell.domain.nutricionista;


import jakarta.validation.constraints.NotNull;

public record NutricionistaAtualizar(
        @NotNull
        Long id,

        String nome ,


        int avatar


) {
}
