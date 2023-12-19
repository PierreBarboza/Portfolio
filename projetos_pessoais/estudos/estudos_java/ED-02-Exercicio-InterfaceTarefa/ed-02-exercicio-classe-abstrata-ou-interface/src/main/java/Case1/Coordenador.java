package Case1;

public class Coordenador implements Bonificavel{
    
        private double qtdHorasTrabalhadas;
        private double valorHorasTrabalhadas;

    public Coordenador(double qtdHorasTrabalhadas, double valorHorasTrabalhadas) {
        this.qtdHorasTrabalhadas = qtdHorasTrabalhadas;
        this.valorHorasTrabalhadas = valorHorasTrabalhadas;
    }

    @Override
    public double getValorBonus() {

        return (((qtdHorasTrabalhadas * valorHorasTrabalhadas) * 4.5) * 0.2);
    }

    @Override
    public String toString() {
        return "Coordenador{" +
                "qtdHorasTrabalhadas=" + qtdHorasTrabalhadas +
                ", valorHorasTrabalhadas=" + valorHorasTrabalhadas +
                '}';
    }
}
