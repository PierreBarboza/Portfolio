package Case1;

import java.util.ArrayList;
import java.util.List;

public class ControleBonus {

    private List<Bonificavel>listBonificaveis;

    public ControleBonus() {
        this.listBonificaveis = new ArrayList<>();
    }

    public void addFuncBonificavel(Bonificavel bonificavel){

        listBonificaveis.add(bonificavel);
    }

    public void exibirTodosBonificaveis(){
        System.out.println("Esses são todos os bonificaveis: ");

        for(Bonificavel boni : listBonificaveis){

            System.out.println(boni);
        }
    }

    public void totalBonus(){

        double totalBoni = 0.0;
        for(Bonificavel boni : listBonificaveis){

            totalBoni += boni.getValorBonus();
        }

        System.out.println(String.format("Esse é o total de bonus: %.2f", totalBoni));
    }
}
