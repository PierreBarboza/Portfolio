package salus.well.projetosaluswell.domain.alimento;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlimentoTest {

    @Test
    public void testExisteNomeAlimento() {

        Alimento alimento = new Alimento(1L, "maca", "fruta", false, false, false, 0.5, 10.0, 0.2, 52.0);
        String nome = "maca";


        boolean resultado = alimento.existeNomeAlimento(nome);


        Assertions.assertTrue(resultado);
    }
}