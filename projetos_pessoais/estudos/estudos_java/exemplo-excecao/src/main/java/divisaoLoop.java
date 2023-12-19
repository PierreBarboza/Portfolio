import javax.swing.*;

public class divisaoLoop {

    public static void main(String[] args) {

        Boolean fim = false;
        while (!fim) {
            String sNum1 = JOptionPane.showInputDialog("Digite o primeiro numero");
            String sNum2 = JOptionPane.showInputDialog("Digite o segundo numero");
            try {
                int num1 = Integer.parseInt(sNum1);
                int num2 = Integer.parseInt(sNum2);

                JOptionPane.showMessageDialog(null, (num1 / num2));
                fim = true;
            }
            catch (ArithmeticException e){
                JOptionPane.showMessageDialog(null, "Divisão por zero!\n" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Digite apenas numeros!\n" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
            }


        };
        System.exit(0);

    }
}
