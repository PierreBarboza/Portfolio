package Case2;

import java.util.ArrayList;
import java.util.List;

public class ControleBonus {

    private List<Educador>listEducador;

    public ControleBonus() {
        this.listEducador = new ArrayList<>();
    }

    public void addFuncBonificavel(Educador edu){

        listEducador.add(edu);


    }

    public void exibirTodosBonificaveis(){
        System.out.println("Todos os bonificaveis: ");

        for(Educador edu : listEducador){

            System.out.println(edu);
        }
    }

    public void totalBonus(){

        double totalBoni = 0.0;
        for(Educador edu : listEducador){

            totalBoni += edu.getValorBonus();
        }

        System.out.println(String.format("Esse é o total de bonus: %.2f", totalBoni));
    }
}
