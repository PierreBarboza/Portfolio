import javax.net.ssl.SNIHostName;
import java.util.Scanner;

public class Main {
    public static void exibirMatriz(int[][]matriz){
        System.out.println("Exibindo matiz normal:");
        //Exibir matriz
        for(int linha=0; linha < matriz.length; linha++){
            for(int coluna=0; coluna < matriz[linha].length; coluna++){

                System.out.print(matriz[linha][coluna]+"\t");


            }
            System.out.println();
        }
    }

    public static void exibirMatrizTransposta(int[][]matriz){
        System.out.println("Exibindo matiz Transposta:");
        //Exibir matriz
        for(int c=0; c < matriz[c].length; c++){
            for(int l=0; l < matriz.length; l++){

                System.out.print(matriz[l][c]+"\t");


            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);


        //Criando Matriz
        int[][] matriz = new int[3][4];

        //Solicita que o usuario crie a matriz
        for(int linha=0; linha < matriz.length; linha++){
            for(int coluna=0; coluna < matriz[linha].length; coluna++){

                System.out.println("Digite o valor de matriz["+linha+"]["+coluna+"]");
                matriz[linha][coluna] = leitor.nextInt();


            }

        }

        exibirMatriz(matriz);
        //Criar matriz de inteiros com valores

        int[][] matriz2 = {{1,2}, {3,4}, {5,6}, {7,8}};
        exibirMatriz(matriz2);

        int[][] matrizLoka = {{2}, {3,4,4,6}, {5,6}, {7,8,0}};
        exibirMatriz(matrizLoka);
        exibirMatrizTransposta(matriz2);

        int[][] matrizLoka2 = new int[3][];

        //Solicita que o usuario crie a matriz
//        for(int linha=0; linha < matrizLoka2.length; linha++){
//            for(int coluna=0; coluna < matrizLoka2[linha].length; coluna++){
//
//                System.out.println("Digite o valor de matrizLoka2["+linha+"]["+coluna+"]");
//                matrizLoka2[linha][coluna] = leitor.nextInt();
//
//
//            }
//
//        }

    }

}
