package salus.well.projetosaluswell.domain.nutricionista;

import jakarta.persistence.*;
import lombok.*;
import salus.well.projetosaluswell.domain.pessoa.Pessoa;



@Table(name = "nutricionistas")
@Entity(name = "Nutricionista")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Nutricionista extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  private String crn;
  private Boolean ativo;

  // private String dataNascimento;


    public Nutricionista(NutricionistaCadastro dados) {
        super(dados.nome(), dados.email(), dados.senha(),dados.avatar(),dados.genero(),dados.endereco(), dados.telefone());
        this.crn = dados.crn();
        this.ativo = true;
    }
    public void excluir() {
        this.ativo = false;
    }


    public void atualizarInformacoes(NutricionistaAtualizar nutricionistaAtualizar) {

        if(nutricionistaAtualizar.nome() != null) {
          setNome(nutricionistaAtualizar.nome());
        }

        if(nutricionistaAtualizar.avatar() != 0){
            setAvatar(nutricionistaAtualizar.avatar());
        }


    }


    @Override
    public String exibir() {
        return null;
    }

    public double getAvaliacao() {
        if(super.getContagemAvaliacao() == 0){
            return super.getAvaliacao();
        }else{
            double media;
            media = super.getAvaliacao() / super.getContagemAvaliacao();
            return media;
        }

    }

    public double avaliacaoTotal(){
        double total = 0.0;

        total += super.getAvaliacao();
        return total;
    }

}
