package salus.well.projetosaluswell.domain.diario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import salus.well.projetosaluswell.domain.alimento.Alimento;
import salus.well.projetosaluswell.domain.cliente.Cliente;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "DiarioAlimentar")
@Table(name = "diario_alimentar")
@Getter
@Setter
@EqualsAndHashCode(of ="id")
public class DiarioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double qtdCalorias;
    private String periodo;
    private boolean consumido;

    private LocalDate dataConsumir;
    private LocalDateTime dataConsumida;


    private String alimentos;

    @ManyToOne
    @JoinColumn(name = "nutricionista_id")
    @JsonIgnore
    private Nutricionista nutricionista;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    public DiarioAlimentar(String descricao, Double qtdCalorias, String periodo, String alimentos, Nutricionista nutricionista, Cliente cliente , LocalDate dataConsumir) {
        this.descricao = descricao;
        this.qtdCalorias = qtdCalorias;
        this.periodo = periodo;
        this.consumido = false;
        this.alimentos = alimentos;
        this.nutricionista = nutricionista;
        this.cliente = cliente;
        this.dataConsumir = dataConsumir;
    }

    public DiarioAlimentar() {
    }

    @Override
    public String toString() {
        return "DiarioAlimentar{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", qtdCalorias=" + qtdCalorias +
                ", periodo='" + periodo + '\'' +
                ", alimento=" + alimentos +
                ", nutricionista=" + nutricionista +
                '}';
    }
}
