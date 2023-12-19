import javax.swing.*;
import java.util.Scanner;

public class ExemploThrow {


    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        try {
            System.out.println("Digite nota 1");
            Double nota1 = leitor.nextDouble();
            if (nota1 < 0 || nota1 > 10){
                throw new LimiteUltrapassadoException("Valor invalido da nota1");

            }

            System.out.println("Digite nota 2");
            Double nota2 = leitor.nextDouble();
            if (nota2 < 0 || nota2 > 10){
                throw new LimiteUltrapassadoException("Valor invalido da nota2");

            }

            System.out.println("Media = "+((nota1+nota2)/2));
        }
        catch (LimiteUltrapassadoException e){
            System.out.println(e + "-" + e.dataHora);
            e.printStackTrace();
        }
        System.exit(0);
    }
}
