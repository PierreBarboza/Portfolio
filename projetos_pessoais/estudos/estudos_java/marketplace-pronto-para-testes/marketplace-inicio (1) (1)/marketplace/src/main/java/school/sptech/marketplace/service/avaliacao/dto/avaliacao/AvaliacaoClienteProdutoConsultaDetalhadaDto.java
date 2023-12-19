package school.sptech.marketplace.service.avaliacao.dto.avaliacao;

public class AvaliacaoClienteProdutoConsultaDetalhadaDto {

    private long id;

    private String comentario;

    private Integer nota;

    public void setId(long id) {
        this.id = id;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
