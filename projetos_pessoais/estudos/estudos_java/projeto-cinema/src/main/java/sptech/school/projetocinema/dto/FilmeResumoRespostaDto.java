package sptech.school.projetocinema.dto;

public class FilmeResumoRespostaDto {

    private String titulo;
    private String diretos;

    public FilmeResumoRespostaDto(String titulo, String diretos) {
        this.titulo = titulo;
        this.diretos = diretos;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretos() {
        return diretos;
    }

    public void setDiretos(String diretos) {
        this.diretos = diretos;
    }
}
