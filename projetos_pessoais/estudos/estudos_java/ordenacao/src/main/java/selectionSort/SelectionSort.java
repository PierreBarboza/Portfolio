package selectionSort;

public class SelectionSort {

    public static void main(String[] args) {

        int[] vetor = {4, 3, 7, 4, 9, 5};

        System.out.println("Vetor antes da ordenação: ");
        for (int i=0; i<6; i++){
            System.out.println(vetor[i]);
        }

        int qtdTroca = 0;
        int auxiliar = 0;
        for (int i=0; i< vetor.length; i++){
            for(int j=i+1; j<vetor.length; j++){
                if(vetor[j] < vetor[i]){
                    qtdTroca ++;

                    auxiliar = vetor[i];
                    vetor[i] = vetor[j];
                    vetor[j] = auxiliar;
                }
            }
        }
        System.out.println();
        System.out.println("Vetor depois da ordenação: ");
        for (int i=0; i<6; i++){
            System.out.println(vetor[i]);
        }

        System.out.println("Quantidade de trocas: "+ qtdTroca);

    }
}
