package salus.well.projetosaluswell.domain.pessoa;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;



@Setter
@Getter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor

public abstract class Pessoa {
//    private List<Consumivel> consumiveis;


    private String nome;
    private String email;
    private String senha;
    private double avaliacao;
    private int avatar;
    private String genero;
    private String endereco;
    @PositiveOrZero
    private int contagemAvaliacao;

    private String telefone;


    public Pessoa( String nome, String email, String senha, int avatar, String genero, String endereco, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.avaliacao = 0.0;
        this.avatar = avatar;
        this.genero = genero;
        this.endereco = endereco;
        this.contagemAvaliacao = 0;
        this.telefone = telefone;

    }

    public Pessoa(String nome, String email, int avatar, String endereco) {
        this.nome = nome;
        this.email = email;


        this.avatar = avatar;

        this.endereco = endereco;
    }

    public abstract String exibir();




}
