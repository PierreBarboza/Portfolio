import java.util.Scanner;

public class Atv3 {
    public static void main(String[] args) {

        String[] listNomesUser = new String[10];
        Scanner leitorNomes = new Scanner(System.in);

        System.out.println("Insira 10 nomes...");
        for(int i=0; i < listNomesUser.length; i++){

            System.out.println("Insira o nome de posição " + i +": ");
            listNomesUser[i] = leitorNomes.next();
        }
        System.out.println("Escreva um nome para ser buscado na lista:");
        String nomeEscolhido = leitorNomes.next();
        for(int i=0; i < listNomesUser.length; i++){

            if(listNomesUser[i].equals(nomeEscolhido)){
                System.out.println(String.format("Nome encontrado no índice %d", i));
                return;
            }
        }

        System.out.println("Nome não encontrado");
    }
}
