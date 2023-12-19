import java.util.Scanner;

public class Atv1 {
    public static void main(String[] args) {
        int[] listNumUser = new int[7];
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Insira 7 números...");
        for(int i=0; i < listNumUser.length; i++){

            System.out.println("Insira o numero: ");
            listNumUser[i] = leitorNum.nextInt();
        }

        System.out.println("Exibindo os elementos do array:");
        for (int numAtual : listNumUser){
            System.out.println(numAtual);
        }

        System.out.println("Exibindo os elementos do array de trás para frente:");
        for(int i=listNumUser.length - 1; i >= 0; i--){

            System.out.println(listNumUser[i]);
        }
    }
}
