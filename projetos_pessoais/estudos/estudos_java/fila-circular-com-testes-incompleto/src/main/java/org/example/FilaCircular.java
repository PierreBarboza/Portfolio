package org.example;

public class FilaCircular {
    int tamanho, inicio, fim;
    String[] fila;

    // Construtor - Recebe a capacidade da fila (tamanho total do vetor)
    public FilaCircular(int capacidade) {
        tamanho = 0;
        inicio = 0;
        fim=0;
        fila = new String[capacidade];
    }

    // Método isEmpty() - Retorna true se a fila está vazia e false caso contrário
    public boolean isEmpty() {
        if(this.tamanho == 0){
            return true;
        }
        return false;
    }

    // Método isFull() - Retorna true se a fila está cheia e false caso contrário
    public boolean isFull() {
        if(this.tamanho == fila.length){
            return true;
        }
        return false;
    }

    // Método insert - Recebe informação a ser inserida na fila
    public void insert(String info) {

        if(!isFull()){
            fila[fim++] = info;
            fim = ((fim) % fila.length);
            tamanho++;

        }else{
            throw new IllegalStateException("Fila circular cheia");
        }
    }

    // Método peek() - Retorna o primeiro da fila, sem remover
    public String peek() {
        return fila[inicio];
    }

    // Método poll() - Retorna o primeiro da fila, removendo-o
    public String poll() {
        if(!isEmpty()){

            String aux = fila[inicio];
            fila[inicio] = null;
            inicio = (inicio + 1)%fila.length;
            tamanho--;
            return aux;

        }

        throw new IllegalArgumentException("Erro");
    }

    // Método exibe() - exibe os elementos da fila
    public void exibe() {

        for(int i=0; i < tamanho; i++){

            System.out.println(fila[i]);
        }

    }

    public int getTamanho() {
        return tamanho;
    }

    public int getInicio() {
        return inicio;
    }

    public int getFim() {
        return fim;
    }

    // Cria um vetor e percorre a fila adicionando os elementos no vetor (
    // Retorna o vetor criado e não a fila
    // Esse método é equivalente ao exibe, mas em vez de exibir os elementos da fila na console,
    // copia os elementos da fila para um vetor, na ordem em que seriam exibidos
    public String[] getFila(){
        String[] filaExibicao = new String[tamanho];
        if (isEmpty()){
            throw new IllegalArgumentException("Erro");

        }
        int contador = 0;
        for (int i=inicio; contador < tamanho; i=(i+1)%fila.length){
            filaExibicao[contador] = fila[i];
            contador ++;

        }
        return filaExibicao;
    }
}

