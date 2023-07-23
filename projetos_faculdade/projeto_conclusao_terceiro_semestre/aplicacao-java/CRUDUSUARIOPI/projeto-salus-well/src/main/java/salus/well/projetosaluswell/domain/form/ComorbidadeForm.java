package salus.well.projetosaluswell.domain.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.cliente.ClienteRepository;
import salus.well.projetosaluswell.domain.comorbidade.Comorbidade;


@Getter
@Setter
public class ComorbidadeForm {


    private Boolean diabete;

    private Boolean colestorol;
    private Boolean hipertensao;
    @NotNull
    @NotEmpty
    private String emailCliente;


    public Comorbidade converter(ClienteRepository clienteRepository){
        Cliente cliente = clienteRepository.findByEmail(emailCliente);
        return new Comorbidade(diabete, colestorol,hipertensao,cliente);
    }

}
