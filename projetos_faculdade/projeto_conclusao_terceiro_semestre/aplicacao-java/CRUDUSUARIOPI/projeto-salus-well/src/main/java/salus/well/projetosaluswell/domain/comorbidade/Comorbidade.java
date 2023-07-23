package salus.well.projetosaluswell.domain.comorbidade;

import jakarta.persistence.*;
import lombok.*;
import salus.well.projetosaluswell.domain.cliente.Cliente;



@Entity(name = "Comorbidade")
@Table(name = "comorbidade")
@Getter
@Setter
@EqualsAndHashCode(of ="id")

public class Comorbidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean diabete;

    private Boolean colesterol;
    private Boolean hipertensao;

    @ManyToOne

    private Cliente cliente;

    public Comorbidade(Boolean diabete, Boolean colesterol, Boolean hipertensao, Cliente cliente) {
        this.diabete = diabete;
        this.colesterol = colesterol;
        this.hipertensao = hipertensao;
        this.cliente = cliente;
    }

    public Comorbidade() {
    }

    public Comorbidade(Long id, boolean diabete, boolean hipertensao, boolean colesterol, Long clienteId) {
    }

    public void atualizarInformacoes(ComorbidadeAtualizar atualizar){
        setDiabete(atualizar.diabete());
        setColesterol(atualizar.colesterol());
        setHipertensao(atualizar.hipertensao());
    }
}
