import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        ListaObj <Jogador> listaJogador = new ListaObj<>(5);

        System.out.println("Bem vindos a Fifa manager");
        System.out.println("Escolha a opção desejada: (digite 1, 2 ou 3)");
        System.out.println("1 - Adiciona um Jogador");
        System.out.println("2 - Exibe Relatório");
        System.out.println("3 - Gravar-csv");
        System.out.println("4 - Ler-cvs");
        System.out.println("5 - Gravar-txt");
        System.out.println("6 - Ler-txt");
        System.out.println("7 - Sair");

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

                    listaJogador.adiciona(j);
                    System.out.println("Jogador adicionado");
                    break;
                case 2:
                    System.out.println("Você escolheu relatorio. Segue o relatorio dos jogadores cadastrados no Fifa manager:");



                    System.out.printf("%4S %6S %-10S %-10S %12S %-8S %-10S\n", "id", "Camisa", "nome", "posicao", "salario", "banco", "lesionado");


                    for (int i = 0; i < listaJogador.getTamanho(); i++) {
                        Jogador jogador = listaJogador.getElemento(i);
                        System.out.printf("%4d %6d %-10s %-10s %12.2f %-8b %-8b\n", jogador.getId(), jogador.getNumCamisa(), jogador.getNome(), jogador.getPosicao(), jogador.getSalario(), jogador.getBanco(), jogador.getLesionado());

                        System.out.println();
                    }

                    break;
                case 3:
                    System.out.println("Iniciando gravação...");
                    Scanner leitorNome = new Scanner(System.in);
                    System.out.println("Informe o nome do arquivo com a extenção dele:");
                    String nomeArq = leitorNome.next();

                    listaJogador.gravarArquivoCsv(listaJogador,nomeArq);
                case 4:
                    System.out.println("Iniciando leitura do arquivo");
                    Scanner i = new Scanner(System.in);
                    System.out.println("Informe o nome do arquivo com a extenção dele:");
                    String nomeArqLeitura = i.next();

                    listaJogador.lerArquivo(nomeArqLeitura);
                    break;
                case 5:
                    System.out.println("Iniciando gravação TXT...");
                    gravarArquivoTxt(listaJogador, "jogadores.txt");

                    System.out.println("Finalizado gravação TXT!");
                    break;
                case 6:
                    System.out.println("Iniciando Leitura TXT...");
                    listaJogador = leArquivoTxt("jogadores.txt");

                    System.out.println("Lista Jogadores:");

                    for(int y=0; y < listaJogador.getTamanho(); y++){
                    System.out.println(listaJogador.getElemento(y));
                    }

                    System.out.println("Finalizado Leitura TXT!");
                    break;
                case 7:
                    System.out.println("Obrigado por usar o Fifa manager. Até a proxima...");
                    break;
                default:
                    System.out.println("O número escolhido é inválido! Digite um número entre 1 a 5.");
            }

        } while (escolhaUser != 7);
    }

    public static void gravarRegistro(String registro, String nomeArq){
        BufferedWriter saida = null;

        try{

            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }catch (IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try{

            saida.append(registro + "\n");
            saida.close();
        }catch (IOException e){
            System.out.println("Erro ao gravar o arquivo");
        }
    }

    public static void gravarArquivoTxt(ListaObj<Jogador> listaJogador, String nomeArq){

        int contaRegDadosGravados=0;

        //Gera o Header
        String header = "00JOGADORES2023";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header +="01";

        gravarRegistro(header, nomeArq);

        //Gera o corpo
        String corpo;
        for(int i=0; i < listaJogador.getTamanho(); i++){
            Jogador a = listaJogador.getElemento(i);
            corpo="02";
            corpo+=String.format("%02d", a.getId()); //O menos alinha para ESQUERDA || O numero após o menos é o tamanho do campo, o numero após o . é o limitador de caracteres
            corpo+=String.format("%02d", a.getNumCamisa());
            corpo+=String.format("%-30.30s", a.getNome());
            corpo+=String.format("%-15.15s", a.getPosicao());
            corpo+=String.format("%05.2f", a.getSalario());
            corpo+=String.format("%5b", a.getBanco());
            corpo+=String.format("%5b", a.getLesionado());

            gravarRegistro(corpo, nomeArq);
            contaRegDadosGravados++;
        }

        //Gera o trailer
        String trailer = "01";
        trailer+= String.format("%010d", contaRegDadosGravados);
        gravarRegistro(trailer, nomeArq);
    }

    public static ListaObj<Jogador> leArquivoTxt(String nomeArq){

        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, posicao;
        Double salario;
        int id;
        int numCamisa;
        Boolean banco, lesionado;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;



        ListaObj <Jogador> listaJogador = new ListaObj<>(5);

        try{

            entrada = new BufferedReader(new FileReader(nomeArq));
        }catch (IOException e){

            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        System.out.println("\nLendo e processando o arquivo...");

        try{
            //Ler o primeiro registro do arquivo
            registro = entrada.readLine();
            while(registro != null){

                tipoRegistro = registro.substring(0,2);
                if(tipoRegistro.equals("00")){
                    System.out.println("É um registro de header");
                    System.out.println("Tipo de arquivo: "+registro.substring(2, 11));
                    System.out.println("Ano: "+registro.substring(11, 15));
                    System.out.println("Data e hora de gravaçõa do arquivo: "+registro.substring(15, 36));
                    System.out.println();
                }else if(tipoRegistro.equals("02")){
                    System.out.println("É um registro de dados (corpo)");
                    id = Integer.parseInt(registro.substring(2,4).trim());
                    numCamisa = Integer.parseInt(registro.substring(4, 6).trim());
                    nome = registro.substring(6, 35).trim();
                    posicao = registro.substring(35, 51).trim();
                    salario = Double.valueOf(registro.substring(51,58).replace(',', '.'));
                    banco = Boolean.valueOf(Boolean.parseBoolean(registro.substring(58,63)));
                    lesionado = Boolean.valueOf(Boolean.parseBoolean(registro.substring(63,68)));

                    contaRegDadosLidos ++;

                    Jogador a = new Jogador(id, numCamisa, nome, posicao, salario, banco, lesionado);
                    listaJogador.adiciona(a);


                }else if(tipoRegistro.equals("01")){
                    System.out.println("É um registro de trailer");
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(2,12));
                    if (qtdRegDadosGravados == contaRegDadosLidos){

                        System.out.println("Quantidade de registros de dados gravados compativel com registro de dados lidos");
                    }else{
                        System.out.println("Quantidade de registros de dados gravados incompativel com registro de dados lidos");
                    }
                }else{
                    System.out.println("Tipo de registro invalido");
                }

                registro = entrada.readLine();
            }
            entrada.close();


        }catch (IOException e){

            System.out.println("Erro ao ler o arquivo");

        }

        return listaJogador;
    }


}
