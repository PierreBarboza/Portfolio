package salus.well.projetosaluswell.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoDiarioConsumido {
    private boolean consumido;

    public DtoDiarioConsumido(boolean consumido) {
        this.consumido = consumido;
    }
}
