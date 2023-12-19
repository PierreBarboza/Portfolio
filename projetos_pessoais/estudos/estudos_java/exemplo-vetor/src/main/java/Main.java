import java.util.Scanner;

public class Main {

    public static void exibeVetor(int[] vetor){
        System.out.println("Exibindo vetor:");

        for(int i=0; i < vetor.length; i++){

            System.out.println("vetor["+i+"]="+ vetor[i]);
        }

    }

    public static String somarVetor(int[] vetor){
        int total = 0;
        for(int vetorAtual: vetor){

            total += vetorAtual;

        }
        return String.format("O vetor atual tem um total de: %d", total);
    }

    public static int paresVetor(int[] vetor){
        int totalPares = 0;
        for(int vetorAtual: vetor){

            if(vetorAtual % 2 == 0){
                totalPares += 1;
            }

        }
        return totalPares;
    }




    public static void main(String[] args) {

        //Criando um vetor:
        int[] vetor = new int[5];
        int[] vetorInit = {1, 5, 3, 7, 3, 2, 6};

        Scanner leitorNum = new Scanner(System.in);

        //Preenchendo um vetor
        //for(int i = 0; i < vetor.length; i++){

            //vetor[i] = i * 10;
        //}

        //for (int i=0; i < vetor.length; i++){
          //  System.out.println("Informe um número:");
            //vetor[i] = leitorNum.nextInt();

        //}

        //exibeVetor(vetor);
        //exibeVetor(vetorInit);

        //Desafio 1
        //Exibir a soma dos valores do vetor
        System.out.println(somarVetor(vetorInit));

        //Desafio 2
        //Exibir a quantidade de elementos pares do vetor
        System.out.println(String.format("O total de numeros pares é: %d", paresVetor(vetorInit)));

        //Desafio 3
        //Criar um vetor de Strings com os nomes dos dias da semana
        //Solicita que o usuário digite um número de 1 a 7
        //Enquanto ele não for um número valido, fica no loop
        //Exibir o dia da semana correspondente ao número digitado
        //Sendo 1 é Domingo, 2 é segunda e assim por diante...
        int[] listNumDigitado = new int[7];
        int rodada = 0;
        do{
            System.out.println("Insira um número de 1 a 7:");
            int numUsuario = leitorNum.nextInt();
            if (numUsuario >= 1 && numUsuario <= 7){
                listNumDigitado[rodada] = numUsuario;
                rodada += 1;
            }else{
                System.out.println("Numero digitado é invalido, insira outro");
            }

        }while (rodada <= 6);
        String[] vetorDiasSemana = {"Domingo, Segunda, Terça, Quarta, Quinta, Sexta, Sabado"};

        for (int elementoAtual: listNumDigitado){
            System.out.println(vetorDiasSemana[elementoAtual - 1]);
        }
    }
}
