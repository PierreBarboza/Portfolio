package Case1;

public class Professor implements Bonificavel {

    private int qtdAulasDadas;
    private double valorAula;

    public Professor(int qtdAulasDadas, double valorAula) {
        this.qtdAulasDadas = qtdAulasDadas;
        this.valorAula = valorAula;
    }

    public double getValorBonus() {

        return (((qtdAulasDadas * valorAula) * 4.5) * 0.15);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "qtdAulasDadas=" + qtdAulasDadas +
                ", valorAula=" + valorAula +
                '}';
    }
}
