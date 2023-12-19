import java.util.Scanner;

public class Atv5 {
    public static void main(String[] args) {
        String[] listNomesCarros = new String[10];
        int[] listRendimentoCarros = new int[10];

        Scanner leitorNomes = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Insira 10 nomes...");
        for(int i=0; i < listNomesCarros.length; i++){

            System.out.println("Insira o nome do carro de posição " + i +": ");
            listNomesCarros[i] = leitorNomes.next();
            System.out.println("Insira o valor de rendimento desse carro:");
            listRendimentoCarros[i] = leitorNum.nextInt();


        }

        int rendimentoAtual = 0;
        int rendimentoIndice = 0;
        for(int i=0; i < listRendimentoCarros.length; i++){

            if(listRendimentoCarros[i] > rendimentoAtual){
                rendimentoAtual = listRendimentoCarros[i];
                rendimentoIndice = i;
            }

        }

        System.out.println("O carro com melhor rendimento é o " + listNomesCarros[rendimentoIndice]);
    }
}
