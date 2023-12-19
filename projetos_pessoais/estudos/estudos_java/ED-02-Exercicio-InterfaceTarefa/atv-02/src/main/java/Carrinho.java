import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    List<Vendavel> cart;

    public Carrinho() {
        this.cart = new ArrayList<>();
    }

    public void adicionaVendavel(Vendavel v){

        cart.add(v);
    }

    public double calculaTotalVenda(){


        Double totalVendavel = 0.0;
        for(Vendavel ven: cart){
            totalVendavel += ven.getValorVenda();
        }

        return totalVendavel;
    }

    public void exibeItensCarrinho(){
        System.out.println("\nEsses são os itens do seu carrinho:\n");

        for(Vendavel ven: cart){


            System.out.print("Item :");
            System.out.println(ven);


        }

    }


}
