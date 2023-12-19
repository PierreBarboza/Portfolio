import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ListaObj <Jogador> listaJogador = new ListaObj<>(5);

        System.out.println("Bem vindos a Fifa manager");
        System.out.println("Escolha a opção desejada: (digite 1, 2 ou 3)");
        System.out.println("1 - Adiciona um Jogador");
        System.out.println("2 - Exibe Relatório");
        System.out.println("3 - Sair");

        int escolhaUser;
        do {
            Scanner leitor = new Scanner(System.in);
            escolhaUser = leitor.nextInt();

            switch (escolhaUser) {
                case 1:
                    System.out.println("Você escolheu adicionar um jogador");
                    Scanner infoTesto = new Scanner(System.in);
                    Scanner infoNum = new Scanner(System.in);
                    Scanner infoDouble = new Scanner(System.in);

                    boolean banco = false;
                    boolean lesionado = false;

                    System.out.println("Insira o id do jogodor:");
                    int idJogador = infoNum.nextInt();
                    System.out.println("Insira numero da camisa do jogodor:");
                    int numCamisaJogador = infoNum.nextInt();
                    System.out.println("Insira o nome do jogodor:");
                    String nomeJogador = infoTesto.next();
                    System.out.println("Insira a posição que o jogodor joga:");
                    String posicaoJogador = infoTesto.next();
                    System.out.println("Insira o salario do jogodor:");
                    Double salarioJogador = infoDouble.nextDouble();
                    System.out.println("O jogador está no banco? (1 - sim | 2 - não)");
                    int bancoJogador = infoNum.nextInt();
                    if(bancoJogador == 1){
                        banco = true;
                    }
                    System.out.println("O jogador está lesionado? (1 - sim | 2 - não)");
                    String lesionadoJogador = infoNum.next();
                    if(bancoJogador == 1){
                        lesionado = true;
                    }

                    Jogador j = new Jogador(idJogador, numCamisaJogador, nomeJogador, posicaoJogador, salarioJogador, banco, lesionado);

                    listaJogador.AddItem(j);
                    System.out.println("Jogador adicionado");
                    break;
                case 2:
                    System.out.println("Você escolheu relatorio. Segue o relatorio dos jogadores cadastrados no Fifa manager:");



                    System.out.printf("%4S %6S %-10S %-10S %12S %-8S %-10S\n", "id", "Camisa", "nome", "posicao", "salario", "banco", "lesionado");


                    for (int i = 0; i < listaJogador.getTamanho(); i++) {
                        Jogador jogador = listaJogador.listaObjetos(i);
                        System.out.printf("%4d %6d %-10s %-10s %12.2f %-8b %-8b\n", jogador.getId(), jogador.getNumCamisa(), jogador.getNome(), jogador.getPosicao(), jogador.getSalario(), jogador.getBanco(), jogador.getLesionado());

                        System.out.println();
                    }

                    break;
                case 3:
                    System.out.println("Obrigado por usar o Fifa manager. Até a proxima...");
                    break;
                default:
                    System.out.println("O número escolhido é inválido! Digite um número entre 1 a 3.");
            }

        } while (escolhaUser != 3);

    }
}
