package sptech.school.nota03.sprint02.biblioteca.classe;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
public class Escritor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String nomeEscritor;

    private String paisNasc;

    @OneToMany(mappedBy = "escritor")
    private List<Livro> livros;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEscritor() {
        return nomeEscritor;
    }

    public void setNomeEscritor(String nomeEscritor) {
        this.nomeEscritor = nomeEscritor;
    }

    public String getPaisNasc() {
        return paisNasc;
    }

    public void setPaisNasc(String paisNasc) {
        this.paisNasc = paisNasc;
    }
}
