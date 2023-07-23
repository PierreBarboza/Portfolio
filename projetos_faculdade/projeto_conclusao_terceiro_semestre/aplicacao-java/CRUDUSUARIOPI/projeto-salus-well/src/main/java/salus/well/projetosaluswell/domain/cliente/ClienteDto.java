package salus.well.projetosaluswell.domain.cliente;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClienteDto {


    private String nome;
    private Long id;


    public ClienteDto(Cliente cliente){
       this.nome = cliente.getNome();
       this.id = cliente.getId();

    }

    public static List<ClienteDto> converter(List<Cliente> lojas) {
        return lojas.stream().map(ClienteDto::new).collect(Collectors.toList());
    }
}
