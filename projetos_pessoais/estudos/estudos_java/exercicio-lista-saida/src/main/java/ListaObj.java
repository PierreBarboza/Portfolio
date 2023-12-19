public class ListaObj <T>{ //Quando fazemos uma lista de objetos genericos temos que colocar esse T que indica que ele generico

    private T[] vetor; //Criamos um vetor generico

    // Tem dupla função: representa quantos elementos foram adicionado no vetor
    // Também o índice de onde será adicionado o próximo elemento
    private int numElem;


    public ListaObj(int tamanho) { // O construtor vai receber apenas o tamanho que queremos criar nosso vetor
        this.vetor = (T[]) new Object[tamanho]; // Iniciamos o nosso vetor com o Object pois ele é a classe que é mãe de toda
        // E passamos o tamanho recebido no construtor
        this.numElem = 0; // Sempre vai começar em 0 pois ele representa a quantidade de elementos que tem no nosso vetor
    }

    public void AddItem(T item){
        if(numElem >= vetor.length){
            throw new IllegalStateException("Lista cheia"); //Essa é a forma como retornamos um erro
        }else{
            vetor[numElem]=item;
            numElem++;
        }
    }

    public int getTamanho(){

        return numElem;
    }

    public T listaObjetos(int indice){
        return vetor[indice];

    }


}
