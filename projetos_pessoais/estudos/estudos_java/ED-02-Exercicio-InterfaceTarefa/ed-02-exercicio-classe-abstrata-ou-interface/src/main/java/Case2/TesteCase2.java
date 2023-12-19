package Case2;

public class TesteCase2 {

    public static void main(String[] args) {
        System.out.println("Iniciando teste...");

        ControleBonus cb = new ControleBonus();
        Professor pd = new Professor("Pedro", 80.0, 1.0);
        Coordenador cd = new Coordenador("Lucas", 60.0, 13.0, 12.0, 9.0);

        cb.addFuncBonificavel(pd);
        cb.addFuncBonificavel(cd);

        cb.exibirTodosBonificaveis();

        cb.totalBonus();

    }
}
