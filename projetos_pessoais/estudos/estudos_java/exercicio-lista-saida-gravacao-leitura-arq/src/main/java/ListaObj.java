import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ListaObj <T> {

    private T[] vetor;

    private int nroElem;

    public ListaObj(int tamanho) {
        this.vetor = (T[]) new Object[tamanho];
        this.nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (this.nroElem >= this.vetor.length) {

            throw new IllegalStateException("Lista cheia");
        }else {
            vetor[nroElem++] = elemento;

        }
    }

    public int busca(T elementoBuscado) {

        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {

        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }else{

            for (int i = indice; i < nroElem-1; i++) {
                vetor[i] = vetor[i+1];
            }
            nroElem--;
            return true;
        }
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {

        if (indice <= nroElem && indice > -1) {
            return vetor[indice];
        }
        return null;
    }

    public void limpa() {
        this.nroElem = 0;
    }

    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista jogadores está vazia.");
        }
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.print(vetor[i] + "\t");
            }
            System.out.println();
        }
    }

    public T[] getVetor() {
        return this.vetor;
    }

    public void gravarArquivoCsv(ListaObj<Jogador> listaJogador, String nomeArq) {

        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        try{
            arq = new FileWriter(nomeArq, true);
            saida = new Formatter(arq);

        }catch(IOException e){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try{
            for (int i =0; i < listaJogador.getTamanho(); i++){

                Jogador jogador = listaJogador.getElemento(i);
                saida.format("%d;%d;%s;%s;%.2f;%b;%b\n", jogador.getId(), jogador.getNumCamisa(), jogador.getNome(), jogador.getPosicao(), jogador.getSalario(), jogador.getBanco(), jogador.getLesionado());
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

    public void lerArquivo(String nomeArquivo) {

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
            System.out.printf("%4S %6S %-10S %-10S %12S %-8S %-10S\n", "id", "Camisa", "nome", "posicao", "salario", "banco", "lesionado");
            while (entrada.hasNext()){

                int id = entrada.nextInt();
                Integer numCamisa = entrada.nextInt();
                String nome = entrada.next();
                String posicao = entrada.next();
                Double salario = entrada.nextDouble();
                Boolean banco = entrada.nextBoolean();
                Boolean lesionado = entrada.nextBoolean();

                System.out.printf("%4S %6S %-10S %-10S %12S %-8S %-10S\n", id, numCamisa, nome, posicao, salario, banco, lesionado);
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
}
