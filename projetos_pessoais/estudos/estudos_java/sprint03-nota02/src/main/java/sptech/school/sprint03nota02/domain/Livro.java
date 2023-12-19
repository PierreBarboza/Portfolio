package sptech.school.sprint03nota02.domain;

import jakarta.persistence.*;
import sptech.school.sprint03nota02.dto.LivroAtualizarDto;

import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeLivro;
    @ManyToOne
    private Escritor escritor;
    private boolean bestSeller;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public Escritor getEscritor() {
        return escritor;
    }

    public void setEscritor(Escritor escritor) {
        this.escritor = escritor;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public void atualizar(LivroAtualizarDto dados) {
        this.nomeLivro = dados.getNomeLivro();
        this.bestSeller = dados.isBestSeller();
    }

}
