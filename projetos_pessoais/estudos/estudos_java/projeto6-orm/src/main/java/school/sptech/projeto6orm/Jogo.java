package school.sptech.projeto6orm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity //Notação para que cada registro do banco seja enterpretado como um objeto
//Agora ele entende que essa classe reflete um registro no banco de dados
public class Jogo {

    @Id //Apontando para o spring qual é o identificador da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Especificando que o id é auto-incremental
    private Long id;

    private String nome;
    private LocalDate anoLancamento;

    public Jogo() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
