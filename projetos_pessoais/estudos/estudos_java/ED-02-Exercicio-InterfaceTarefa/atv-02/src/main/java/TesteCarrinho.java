import java.util.Scanner;

public class TesteCarrinho {


    public static void main(String[] args) {

        Carrinho cr = new Carrinho();



        Scanner entrada = new Scanner(System.in);

        Integer numEscolhido = 0;

        do{
            Scanner entradaTxt = new Scanner(System.in);
            Scanner entradaNum = new Scanner(System.in);
            Scanner entradaDouble = new Scanner(System.in);

            System.out.println("Escolha uma das seguintes opções:");
            System.out.printf("\n1 - Adiciona livro\n2 - Adiciona DVD\n3 - Adiciona Serviço\n4 - Exibir itens do carrinho\n5 - Exibir total de venda\n6 - fim\n");
            numEscolhido = entrada.nextInt();

            switch (numEscolhido) {
                case 1:
                    System.out.println("\nAdicionando livro...\n");

                    System.out.println("Informe o codigo do livro:");
                    Integer codLivro = entradaNum.nextInt();

                    System.out.println("Informe o valor de custo do livro:");
                    Double valorCustoLivro = entradaDouble.nextDouble();

                    System.out.println("Informe o nome do livro:");
                    String nomeLivro = entradaTxt.nextLine();

                    System.out.println("Informe o autor do livro:");
                    String autorLivro = entradaTxt.nextLine();

                    System.out.println("Informe o ISBN do livro:");
                    String isbnLivro = entradaTxt.nextLine();

                    Livro lv = new Livro(codLivro, 88.01, nomeLivro, autorLivro, isbnLivro);

                    cr.adicionaVendavel(lv);
                    System.out.println("Livro adicionado com sucesso!");

                    break;
                case 2:
                    System.out.println("\nAdicionar DVD:\n");

                    System.out.println("Informe o codigo do dvd:");
                    Integer codDvd = entradaNum.nextInt();

                    System.out.println("Informe o valor de custo do dvd:");
                    Double valorCustoDvd = entradaDouble.nextDouble();

                    System.out.println("Informe o nome do dvd:");
                    String nomeDvd = entradaTxt.nextLine();

                    System.out.println("Informe a gravadora do dvd:");
                    String gravadoraDvd = entradaTxt.nextLine();

                    Dvd dvd = new Dvd(codDvd, valorCustoDvd, nomeDvd, gravadoraDvd);

                    cr.adicionaVendavel(dvd);
                    System.out.println("Dvd adicionado com sucesso!");

                    break;
                case 3:
                    System.out.println("\nAdicionar serviço:\n");

                    System.out.println("Informe descrição do serviço:");
                    String descricaoServico = entradaTxt.nextLine();

                    System.out.println("Informe o codigo do serviço:");
                    Integer codServico = entradaNum.nextInt();

                    System.out.println("Informe a quantodade de horas do serviço:");
                    Integer qtdHoras = entradaNum.nextInt();

                    System.out.println("Informe o valor da hora do serviço:");
                    Double valorhora = entradaDouble.nextDouble();

                    Servico sr = new Servico(descricaoServico, codServico, qtdHoras, valorhora);

                    cr.adicionaVendavel(sr);
                    System.out.println("Serviço adicionado com sucesso!");

                    break;
                case 4:

                    cr.exibeItensCarrinho();
                    break;

                case 5:

                    System.out.println("O total do seu carrinho: ");

                    System.out.println(String.format("Total = %.2f", cr.calculaTotalVenda()));
                    break;
                case 6:
                    System.out.println("\nObriga por comprar conosco, até a proxima...\n");
                    break;
                default:
                    System.out.println("O número escolhido é inválido! Digite um número entre 1 a 10.");
            };

        }while(numEscolhido != 6);



    }
}
