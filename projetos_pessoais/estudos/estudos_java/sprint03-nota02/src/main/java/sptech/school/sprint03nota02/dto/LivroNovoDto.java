package sptech.school.sprint03nota02.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import sptech.school.sprint03nota02.domain.Escritor;

public class LivroNovoDto {

    private String nomeLivro;

    private Escritor escritor;
    private boolean bestSeller;

    public LivroNovoDto(String nomeLivro, Escritor escritor, boolean bestSeller) {
        this.nomeLivro = nomeLivro;
        this.escritor = escritor;
        this.bestSeller = bestSeller;
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
}
