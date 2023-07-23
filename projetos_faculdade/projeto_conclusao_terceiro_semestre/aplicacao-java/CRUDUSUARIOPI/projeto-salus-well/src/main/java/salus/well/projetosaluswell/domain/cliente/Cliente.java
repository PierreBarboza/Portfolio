package salus.well.projetosaluswell.domain.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import salus.well.projetosaluswell.domain.nutricionista.Nutricionista;
import salus.well.projetosaluswell.domain.pessoa.Pessoa;




@Table(name = "clientes")
@Entity
@AllArgsConstructor

public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    private boolean comorbidade;

    @JsonIgnore
    @Column(length = 10 * 1024 * 1024)
    private byte[] relatorioTxt;
    private Boolean ativo;
    @ManyToOne
    @JoinColumn(name = "nutricionista_id")
    @JsonIgnore
    private Nutricionista nutricionista;

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public boolean isComorbidade() {
        return comorbidade;
    }

    public void setComorbidade(boolean comorbidade) {
        this.comorbidade = comorbidade;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }




    public Cliente(String nome, String email, String endereco, int avatar, boolean ativo) {
        super(nome, email, avatar,  endereco);
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Cliente() {
    }



    public Cliente(ClienteCadastro dados) {

        super(dados.nome(), dados.email(), dados.senha(),dados.avatar(),dados.genero(),dados.endereco(), dados.telefone());

        this.comorbidade = dados.comorbidade();
        this.ativo = true;
    }
    public void excluir() {
        this.ativo = false;
    }

    @Override
    public String exibir() {
        return null;
    }

    public void atualizarInformacoes(ClienteAtualizar clienteAtualizar) {

        if(clienteAtualizar.nome() != null) {
            setNome(clienteAtualizar.nome());
        }

        if(clienteAtualizar.avatar() != 0){
            setAvatar(clienteAtualizar.avatar());
        }


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

    @Override
    public String toString() {
        return "Cliente{" +
               "nome" + super.getNome() +
               "email" + super.getEmail() +
               "endereco" + super.getEndereco() +
               "endereco" + super.getAvatar() +
                ", comorbidade=" + comorbidade +
                ", ativo=" + ativo +
                '}';
    }
}
