public class Livro extends Produto{

    private String nome;
    private String autor;
    private String isbn;

    public Livro(Integer codigo, Double procoCusto, String nome, String autor, String isbn) {
        super(codigo, procoCusto);
        this.nome = nome;
        this.autor = autor;
        this.isbn = isbn;
    }

    @Override
    public Double getValorVenda() {
        return getProcoCusto() * 1.10;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", Valor de venda='" + getValorVenda() + '\'' +
                "} " + super.toString();
    }
}
