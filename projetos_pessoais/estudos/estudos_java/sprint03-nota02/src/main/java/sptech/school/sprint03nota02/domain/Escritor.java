package sptech.school.sprint03nota02.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Escritor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeEscritor;

    @OneToMany(mappedBy = "escritor")
    private List<Livro> livros;

    private LocalDate dataNasc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeEscritor() {
        return nomeEscritor;
    }

    public void setNomeEscritor(String nomeEscritor) {
        this.nomeEscritor = nomeEscritor;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
}
