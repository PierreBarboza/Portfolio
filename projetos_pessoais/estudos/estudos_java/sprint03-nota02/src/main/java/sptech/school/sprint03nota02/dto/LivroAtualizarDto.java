package sptech.school.sprint03nota02.dto;

import sptech.school.sprint03nota02.domain.Escritor;

import java.time.LocalDate;

public class LivroAtualizarDto {

    private String nomeLivro;
    private boolean bestSeller;

    public LivroAtualizarDto(String nomeLivro, boolean bestSeller, Long id) {
        this.nomeLivro = nomeLivro;
        this.bestSeller = bestSeller;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public boolean isBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        this.bestSeller = bestSeller;
    }
}
