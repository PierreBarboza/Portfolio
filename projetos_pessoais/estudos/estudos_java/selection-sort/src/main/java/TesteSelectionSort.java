public class TesteSelectionSort {

    public static void main(String[] args) {

        int[] vetor = {4, 3, 7, 4, 9, 5};
        System.out.println("Vetor antes da ordenação: " + vetor);

        int qtdTroca = 0;
        int auxiliar = 0;
        for (int i=0; i<6; i++){
            for(int j=i+1; j<6; j++){
                if(vetor[j] < vetor[i]){
                    qtdTroca ++;

                    auxiliar = vetor[i];
                    vetor[i] = vetor[j];
                    vetor[j] = auxiliar;
                }
            }
        }

        System.out.println("Vetor depois da ordenação: " + vetor);
    }
}
