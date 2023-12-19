import java.util.Scanner;

public class Atv6 {
    public static void main(String[] args) {


        int[] listMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        Scanner leitorNomes = new Scanner(System.in);
        Scanner leitorNum = new Scanner(System.in);

        System.out.println("Insira um numero de 1 a 31:");
        int diaEscolhido = leitorNum.nextInt();
        System.out.println("Insira um numero de 1 a 12:");
        int mesEscolhido = leitorNum.nextInt();

        int somaDias = diaEscolhido;
        for(int i=0; i < mesEscolhido - 1; i++){

            somaDias += listMes[i];
        }
        System.out.println(String.format("O dia %d/%d corresponde ao %d° dia do ano.", diaEscolhido, mesEscolhido, somaDias));
    }
}
