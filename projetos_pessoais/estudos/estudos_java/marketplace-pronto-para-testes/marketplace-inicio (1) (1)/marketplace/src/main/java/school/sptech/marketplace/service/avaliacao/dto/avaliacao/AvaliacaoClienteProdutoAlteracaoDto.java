package school.sptech.marketplace.service.avaliacao.dto.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class AvaliacaoClienteProdutoAlteracaoDto {

    @Size(min = 1, max = 255)
    private String comentario;

    @Min(0)
    @Max(5)
    private Integer nota;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
