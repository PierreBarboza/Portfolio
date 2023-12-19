import java.util.Scanner;

public class Atv2 {

    public static int somarVetor(int[] vetor){
        int total = 0;
        for(int vetorAtual: vetor){

            total += vetorAtual;

        }
        int media = (total / 10);
        return media;
    }
    public static void main(String[] args) {
        int[] listNumUser = new int[10];
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Insira 10 números...");
        for(int i=0; i < listNumUser.length; i++){

            System.out.println("Insira o numero de posição " + i +": ");
            listNumUser[i] = leitorNum.nextInt();
        }
        int media = somarVetor(listNumUser);
        System.out.println(String.format("O vetor atual tem uma media de: %d", media));
        System.out.println("Apenas elementos acima da media:");
        for(int elementoAtual: listNumUser){

            if(elementoAtual > media){
                System.out.println(elementoAtual);
            }

        }

    }
}
