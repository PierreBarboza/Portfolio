package atipedroleite.valendonota2.questao2.classes;

public class ProdutoReduzido {

    private String nome;
    private double preco;


    public ProdutoReduzido(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
