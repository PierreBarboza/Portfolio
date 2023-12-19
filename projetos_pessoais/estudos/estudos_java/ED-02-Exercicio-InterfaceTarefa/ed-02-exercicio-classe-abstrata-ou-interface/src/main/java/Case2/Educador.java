package Case2;

public abstract class Educador {

    private String nome;
    private double qtdHorasAula;
    private double valorHoraAula;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getQtdHorasAula() {
        return qtdHorasAula;
    }

    public void setQtdHorasAula(double qtdHorasAula) {
        this.qtdHorasAula = qtdHorasAula;
    }

    public double getValorHoraAula() {
        return valorHoraAula;
    }

    public void setValorHoraAula(double valorHoraAula) {
        this.valorHoraAula = valorHoraAula;
    }

    public Educador(String nome, double qtdHorasAula, double valorHoraAula) {
        this.nome = nome;
        this.qtdHorasAula = qtdHorasAula;
        this.valorHoraAula = valorHoraAula;
    }

    public abstract double getValorBonus();
}
