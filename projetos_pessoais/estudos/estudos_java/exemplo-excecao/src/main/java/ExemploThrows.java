import javax.swing.*;

public class ExemploThrows {

    public static int divide(int a, int b)throws ArithmeticException{
        return a/b;
    }

    public static void main(String[] args) {

        String sNum1 = JOptionPane.showInputDialog("Digite o primeiro numero");
        String sNum2 = JOptionPane.showInputDialog("Digite o segundo numero");
        try {
            int num1 = Integer.parseInt(sNum1);
            int num2 = Integer.parseInt(sNum2);

            JOptionPane.showMessageDialog(null, divide(num1, num2));
        }
        catch (ArithmeticException e){
            System.out.println("Divisão por zero");
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            System.out.println("Digite apenas numeros!");
            e.printStackTrace();
        }
        System.exit(0);
    }
}
