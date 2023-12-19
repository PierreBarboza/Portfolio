package Case2;

public class Professor extends Educador{


    public Professor(String nome, double qtdHorasAula, double valorHoraAula) {
        super(nome, qtdHorasAula, valorHoraAula);
    }

    @Override
    public double getValorBonus() {
        return (((getQtdHorasAula() * getValorHoraAula()) * 4.5) * 0.15);

    }


}
