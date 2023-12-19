public class Dvd extends Produto{

    private String nome;
    private String gravadora;

    public Dvd(Integer codigo, Double procoCusto, String nome, String gravadora) {
        super(codigo, procoCusto);
        this.nome = nome;
        this.gravadora = gravadora;
    }

    @Override
    public Double getValorVenda() {
        return getProcoCusto() * 1.10;
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "nome='" + nome + '\'' +
                ", gravadora='" + gravadora + '\'' +
                ", Valor de venda='" + getValorVenda() + '\'' +
                "} " + super.toString();
    }
}
