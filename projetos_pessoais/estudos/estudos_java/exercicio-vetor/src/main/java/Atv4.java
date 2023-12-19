import java.util.Scanner;

public class Atv4 {

    public static void main(String[] args) {
        int[] listNumUser = new int[10];
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Insira 10 números...");
        for(int i=0; i < listNumUser.length; i++){

            System.out.println("Insira o numero de posição " + i +": ");
            listNumUser[i] = leitorNum.nextInt();
        }
        System.out.println("Digite um numero qualquer:");
        int numAleatorio = leitorNum.nextInt();
        int quantidadeDeNumAleatorio = 0;
        for(int elementoAtual: listNumUser){

            if(elementoAtual == numAleatorio){
                quantidadeDeNumAleatorio ++;

            }

        }
        System.out.println(String.format("O numero %d aparece %d vezes no array", numAleatorio, quantidadeDeNumAleatorio));
    }
}
