package bubbleSort;

public class BubbleSort {

    public static void main(String[] args) {

        int[] vetor = {4, 3, 7, 4, 9, 5, 8, 3};
        int aux = 0;

        System.out.println("Vetor antes da ordenação: ");
        for (int i=0; i<vetor.length; i++){
            System.out.println(vetor[i]);
        }

        for(int i = 0; i < vetor.length - 1; i++){
            for(int j = 1; j < vetor.length - i; j++){
                if(vetor[j-1] > vetor[j]){
                    aux = vetor[j];
                    vetor[j] = vetor[j-1];
                    vetor[j-1] = aux;
                }

            }
        }

        System.out.println("Vetor depois da ordenação: ");
        for (int i=0; i<vetor.length; i++){
            System.out.println(vetor[i]);
        }
    }
}
