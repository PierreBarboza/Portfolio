public class ListaEstatica {

    private int[] vetor;
    private int nroElem;

    public ListaEstatica(int tamanho) {
        vetor = new int[tamanho];
        nroElem = 0;
    }


    public void adiciona(int elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
        }else {
            vetor[nroElem++] = elemento;

        }
    }


    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.print(vetor[i] + "\t");
            }
            System.out.println();
        }
    }


    public int busca(int elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i] == elementoBuscado) {
                return i;
            }
        }
        return -1;
    }


    public boolean removePeloIndice (int indice) {

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


    public boolean removeElemento(int elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }


    public boolean substituir(int numeroAntigo, int numeroNovo){
        int posicaoNumeroAntigo = busca(numeroAntigo);

        if(posicaoNumeroAntigo != -1){
            vetor[posicaoNumeroAntigo] = numeroNovo;
            System.out.println("Valor substituido");
            return true;
        }else{

            System.out.println("Valor não encontrado!");
            return false;
        }
    }


    public int contarOcorrencias(int numDigitado){

        int contador = 0;
        for (int i = 0; i< vetor.length; i++){

            if (vetor[i] == numDigitado) {
                contador++;
            }
        }

        return contador;
    }


    public boolean adicionaNoInicio(int numDigitado){

        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
            return false;
        }else {

            for(int i = vetor.length - 1; i >= 0; i--){

                if(i != 0){
                    vetor[i] = vetor[i-1];
                }else{
                    vetor[i] = numDigitado;
                }

            }
            nroElem++;

            System.out.println("Numero adicionado ao vetor: "+numDigitado);
            return true;

        }
    }

}
