package salus.well.projetosaluswell.domain.cliente;


import jakarta.validation.constraints.NotNull;

public record ClienteAtualizar(
        @NotNull
        Long id,
        String nome ,

        int avatar


) {
}
