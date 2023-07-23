package salus.well.projetosaluswell.domain.diario;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;

import static org.junit.jupiter.api.Assertions.*;

public class DiarioAlimentarTest {


    @Mock
    private Nutricionista nutricionistaMock;

    @Mock
    private Cliente clienteMock;

    @Test
    public void testConstructorAndGetters() {
        Long id = 1L;
        String descricao = "teste";
        Double qtdCalorias = 100.0;
        String periodo = "manha";
        String alimentos = "Maca, Banana";
        LocalDate dataCriada = LocalDate.now();
        LocalDate dataConsumir = LocalDate.of(2023, 5, 20);

        DiarioAlimentar diario = new DiarioAlimentar(descricao, qtdCalorias, periodo, alimentos, nutricionistaMock, clienteMock, dataConsumir);
        diario.setId(id);


        assertEquals(id, diario.getId());
        assertEquals(descricao, diario.getDescricao());
        assertEquals(qtdCalorias, diario.getQtdCalorias());
        assertEquals(periodo, diario.getPeriodo());

        assertEquals(dataConsumir, diario.getDataConsumir());
        assertEquals(alimentos, diario.getAlimentos());
        assertEquals(nutricionistaMock, diario.getNutricionista());
        assertEquals(clienteMock, diario.getCliente());
    }
}