package salus.well.projetosaluswell.controller;

import salus.well.projetosaluswell.domain.alimento.Alimento;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class FilaObj <T>{
    private T[] fila;
    private int tamanho;

    public FilaObj(int capacidade) {
        this.fila = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }

    public boolean isEmpty() {
        if(tamanho == 0){
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if(tamanho == fila.length){
            return true;
        }
        return false;
    }

    public void insert(T info) {
        if(!isFull()){
            fila[tamanho++] = info;
        }else{
            throw new IllegalStateException("Fila cheia!");
        }

    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T aux = fila[0];

        if(!isEmpty()){
            for(int i = 1; i < getTamanho(); i++){
                fila[i-1] = fila[i];
            }
            this.tamanho--;
            fila[tamanho] = null;

        }

        return aux;

    }

    public void gravaArquivoCsv(String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;

        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
            System.exit(1);
        }


        try {
            saida.format("%S;%S;%S;%S;%S;%S\n", "nome", "tipo", "proteina", "carboidrato", "gordura total", "calorias");

            for (int i = 0; i < tamanho; i++) {
                Alimento alimento = (Alimento) this.fila[i];
                saida.format("%s;%s;%.2f;%.2f;%.2f;%.2f\n", alimento.getNome(), alimento.getTipo(), alimento.getProteina(),
                        alimento.getCarboidrato(), alimento.getGorduraTotal(), alimento.getCalorias());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        } finally {
            saida.close();

            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo!");
                erro.printStackTrace();
                deuRuim = true;
            }

            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public int getTamanho(){
        return this.tamanho;
    }
}
