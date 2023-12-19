import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Aluno> lista = new ArrayList<>();

        lista.add(new Aluno("0122187", "Leonardo", "ADS", "Estrutura de dados", 9.0, 5));
        lista.add(new Aluno("0122345", "Leo", "CIS", "Prog. Web", 6.0, 15));
        lista.add(new Aluno("0122577", "Nardo", "SO", "Socio", 5.0, 12));
        lista.add(new Aluno("0122658", "Lucas", "Redes", "Socio", 5.0, 12));

        gravarArquivoTxt(lista, "alunos.txt");
        leArquivoTxt("alunos.txt");
    }

    //Metodo responsavel por abrir o arquivo e gravar de registro a registro.
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

    public static void gravarArquivoTxt(List<Aluno>lista, String nomeArq){

        int contaRegDadosGravados=0;

        //Gera o Header
        String header = "00NOTA2023";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header +="01";

        gravarRegistro(header, nomeArq);

        //Gera o corpo
        String corpo;
        for(int i=0; i < lista.size(); i++){
            Aluno a = lista.get(i);
            corpo="02";
            corpo+=String.format("%-5.5s", a.getCurso()); //O menos alinha para ESQUERDA || O numero após o menos é o tamanho do campo, o numero após o . é o limitador de caracteres
            corpo+=String.format("%-8.8s", a.getRa());
            corpo+=String.format("%-50.50s", a.getNome());
            corpo+=String.format("%-40.40s", a.getDisciplina());
            corpo+=String.format("%05.2f", a.getMedia());
            corpo+=String.format("%03d", a.getQtdFalta());

            gravarRegistro(corpo, nomeArq);
            contaRegDadosGravados++;
        }

        //Gera o trailer
        String trailer = "01";
        trailer+= String.format("%010d", contaRegDadosGravados);
        gravarRegistro(trailer, nomeArq);
    }

    public static void leArquivoTxt(String nomeArq){

        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String curso, ra, nome, disciplina;
        Double media;
        int qtdFalta;
        int qtdRegDadosGravados;
        int contaRegDadosLidos = 0;

        List<Aluno> listaLida = new ArrayList<>();

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
                    System.out.println("Tipo de arquivo: "+registro.substring(2, 6));
                    System.out.println("Ano e semestre: "+registro.substring(6, 11));
                    System.out.println("Data e hora de gravaçõa do arquivo: "+registro.substring(11, 29));
                    System.out.println("Versão do documento: "+registro.substring(29, 31));
                    System.out.println();
                }else if(tipoRegistro.equals("02")){
                    System.out.println("É um registro de dados (corpo)");
                    curso = registro.substring(2,7).trim();
                    ra = registro.substring(7, 15).trim();
                    nome = registro.substring(15, 65).trim();
                    disciplina = registro.substring(65, 105).trim();
                    media = Double.valueOf(registro.substring(105,110).replace(',', '.'));
                    qtdFalta = Integer.parseInt(registro.substring(110,113));

                    contaRegDadosLidos ++;

                    Aluno a = new Aluno(ra, nome, curso, disciplina, media, qtdFalta);
                    listaLida.add(a);


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

        System.out.println("Lista:");
        for(Aluno a : listaLida){
            System.out.println(a);
        }
    }
}
