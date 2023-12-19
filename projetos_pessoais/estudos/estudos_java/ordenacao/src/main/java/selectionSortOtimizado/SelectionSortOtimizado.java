package selectionSortOtimizado;

public class SelectionSortOtimizado {

    public static void main(String[] args) {

        int[] vetor = {4, 3, 7, 4, 9, 5};
        int aux = 0;

        System.out.println("Vetor antes da ordenação: ");
        for (int i=0; i<vetor.length; i++){
            System.out.println(vetor[i]);
        }

        int qtdTroca = 0;
        int indMenor = 0;
        for (int i=0; i<vetor.length - 1; i++){
            indMenor = i;
            for(int j=i+1; j<vetor.length; j++){
                if(vetor[j] < vetor[indMenor]){
                    qtdTroca ++;
                    indMenor = j;

                }
            }
            aux = vetor[i];
            vetor[i] = vetor[indMenor];
            vetor[indMenor] = aux;
        }
        System.out.println();
        System.out.println("Vetor depois da ordenação: ");
        for (int i=0; i<vetor.length; i++){
            System.out.println(vetor[i]);
        }
    }
}
