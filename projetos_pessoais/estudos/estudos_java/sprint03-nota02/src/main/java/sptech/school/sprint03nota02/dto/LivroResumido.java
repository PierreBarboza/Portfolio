package sptech.school.sprint03nota02.dto;

public class LivroResumido {

    private String nomeLivroResumido;
    private String escritor;


    public LivroResumido(String nomeLivroResumido, String escritor) {
        this.nomeLivroResumido = nomeLivroResumido;
        this.escritor = escritor;
    }

    public String getNomeLivroResumido() {
        return nomeLivroResumido;
    }

    public void setNomeLivroResumido(String nomeLivroResumido) {
        this.nomeLivroResumido = nomeLivroResumido;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }
}
