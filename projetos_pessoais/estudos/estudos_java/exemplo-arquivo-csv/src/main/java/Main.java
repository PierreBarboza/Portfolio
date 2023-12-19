import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {



    public static void main(String[] args) {

        ListaObj <Cachorro> listaDog = new ListaObj<>(7);

        listaDog.adiciona((new Cachorro(1, "Chico", "pequeno", 10.0)));
        listaDog.adiciona((new Cachorro(2, "Lola", "pequeno", 3.0)));
        listaDog.adiciona((new Cachorro(3, "Fiona", "pequeno", 7.0)));
        listaDog.adiciona((new Cachorro(4, "Thunder", "Grande", 30.0)));
        listaDog.adiciona((new Cachorro(5, "Clifford", "Grande", 90.0)));
        listaDog.adiciona((new Cachorro(7, "Flash", "Pequeno", 9.0)));

        listaDog.exibe();
        //gravarArquivoCsv(listaDog, "dogs");
        lerArquivo("dogs.csv");
    }

    private static void lerArquivo(String nomeArquivo) {

        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        try{
            arq = new FileReader(nomeArquivo);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        try{
            System.out.printf("%4S %-15S %-9S %4S\n", "id", "nome", "porte", "peso");
            while (entrada.hasNext()){

                int id = entrada.nextInt();
                String nome = entrada.next();
                String porte = entrada.next();
                Double peso = entrada.nextDouble();

                System.out.printf("%4d %-15s %-9s %4.1f\n", id, nome, porte, peso);
            }
        }
        catch(NoSuchElementException e){
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        }
        catch(IllegalStateException e){
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        }
        finally {
            entrada.close();
            try{
                arq.close();
            }
            catch (IOException e){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if(deuRuim){
                System.exit(1);
            }
        }
    }

    private static void gravarArquivoCsv(ListaObj<Cachorro> listaDog, String nomeArq) {

        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";

        //Try para abrir o arquivo
        try{
            arq = new FileWriter(nomeArq, true);
            saida = new Formatter(arq);

        }catch(IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        //Try para gravar o arquivo
        try{
            for (int i =0; i < listaDog.getTamanho(); i++){

                Cachorro dog = listaDog.getElemento(i);
                saida.format("%d;%s;%s;%.2f\n", dog.getId(), dog.getNome(), dog.getPorte(), dog.getPeso());
            }
        }
        catch (FormatterClosedException e){

            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try{
                arq.close();
            }
            catch (IOException e){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if(deuRuim){
                System.exit(1);
            }
        }
    }
}
