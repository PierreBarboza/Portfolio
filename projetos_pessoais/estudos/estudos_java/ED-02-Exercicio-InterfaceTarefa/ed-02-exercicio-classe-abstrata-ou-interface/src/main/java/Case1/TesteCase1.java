package Case1;

public class TesteCase1 {

    public static void main(String[] args) {

        System.out.println("Iniciando teste...");

        ControleBonus cb = new ControleBonus();
        Professor pd1 = new Professor(10, 1.00);
        Coordenador co = new Coordenador(10.0, 1.00);

        cb.addFuncBonificavel(pd1);
        cb.addFuncBonificavel(co);

        cb.exibirTodosBonificaveis();

        cb.totalBonus();


    }

}
