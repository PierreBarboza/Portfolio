package salus.well.projetosaluswell.domain.form;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.alimento.AlimentoRepository;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.diario.DiarioAlimentar;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DiarioAlimentarFormTest {

    private DiarioAlimentarForm diarioAlimentarForm;

    @Mock
    private NutricionistaRepository nutricionistaRepository;

    @Mock
    private AlimentoRepository alimentoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        diarioAlimentarForm = new DiarioAlimentarForm();
        diarioAlimentarForm.setDescricao("oooo");
        diarioAlimentarForm.setQtdCalorias(100.0);
        diarioAlimentarForm.setPeriodo("tarde");
        diarioAlimentarForm.setAlimentos("Maca");
        diarioAlimentarForm.setIdNutricionista(1L);
        diarioAlimentarForm.setIdCliente(1L);
        diarioAlimentarForm.setDataConsumir(LocalDate.now());
    }

    @Test
    public void testConverter() {
        Nutricionista nutricionista = new Nutricionista();
        when(nutricionistaRepository.getReferenceById(1L)).thenReturn(nutricionista);




        Cliente cliente = new Cliente();
        when(clienteRepository.getReferenceById(1L)).thenReturn(cliente);

        List<DiarioAlimentar> listaEsperada = new ArrayList<>();
        listaEsperada.add(new DiarioAlimentar("oooo", 100.0, "tarde", "Maca", nutricionista, cliente, LocalDate.now()));
        listaEsperada.add(new DiarioAlimentar("oooo", 100.0, "tarde", "Maca", nutricionista, cliente, LocalDate.now()));

      DiarioAlimentar listaConvertida = diarioAlimentarForm.converter(nutricionistaRepository, clienteRepository);


        assertEquals(listaEsperada.get(0).getDescricao(), listaConvertida.getDescricao());
        assertEquals(listaEsperada.get(1).getDescricao(), listaConvertida.getDescricao());
    }
}