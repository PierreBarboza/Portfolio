package salus.well.projetosaluswell.domain.form;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.alimento.AlimentoRepository;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.diario.DiarioAlimentar;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;
import salus.well.projetosaluswell.domain.nutricionista.NutricionistaRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiarioAlimentarForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String descricao;
    @PositiveOrZero
    private Double qtdCalorias;
    @NotNull @NotEmpty @Length(min = 5)
    private String periodo;

    private String alimentos;

    private Long idNutricionista;

    private Long idCliente;



    private LocalDate dataConsumir;


    public DiarioAlimentar converter(NutricionistaRepository nutricionistaRepository,
                                           ClienteRepository clienteRepository){
    Nutricionista nutricionista = nutricionistaRepository.getReferenceById(idNutricionista);
       // List<DiarioAlimentar> listaDiario = new ArrayList<>();

        Cliente cliente = clienteRepository.getReferenceById(idCliente);

        //List<Alimento> alimentos = alimentoRepository.findByIdIn(idAlimento);

       /* for (Alimento alimento : alimentos) {
            listaDiario.add(new DiarioAlimentar(descricao, qtdCalorias, periodo, alimento, nutricionista, cliente, dataConsumir));
        }
        */

        return new DiarioAlimentar(descricao, qtdCalorias, periodo, alimentos, nutricionista, cliente, dataConsumir);
}


}
