package Case2;

public class Coordenador extends Educador{

    private double qtdHoraCood;
    private double valorHoraCood;

    public Coordenador(String nome, double qtdHorasAula, double valorHoraAula, double qtdHoraCood, double valorHoraCood) {
        super(nome, qtdHorasAula, valorHoraAula);
        this.qtdHoraCood = qtdHoraCood;
        this.valorHoraCood = valorHoraCood;
    }

    @Override
    public double getValorBonus() {
        return (((getQtdHorasAula() * getValorHoraAula()) * 4.5) * 0.15) + (((this.qtdHoraCood * this.valorHoraCood) * 4.5) * 0.2);
    }
}
