package salus.well.projetosaluswell.controller;

public class PilhaObj<T>{
    private T[] elementos;
    private int topo;

    public PilhaObj(int capacidade) {
        elementos = (T[]) new Object[capacidade];
        topo = -1;
    }

    public void empilhar(T elemento) {
        if (topo < elementos.length - 1) {
            topo++;
            elementos[topo] = elemento;
        } else {
            System.out.println("A pilha está cheia.");
        }
    }

    public T desempilhar() {
        if (!estaVazia()) {
            T elemento = elementos[topo];
            topo--;
            return elemento;
        } else {
            System.out.println("A pilha está vazia.");
            return null;
        }
    }

    public T peek() {
        if(estaVazia()){
            return null;
        }
        return elementos[topo];
    }

    public boolean estaVazia() {
        return topo == -1;
    }

    public void exibirPilha() {
        if (estaVazia()) {
            System.out.println("A pilha está vazia.");
        } else {
            System.out.println("Elementos da pilha:");
            for (int i = topo; i >= 0; i--) {
                System.out.println(elementos[i]);
            }
        }
    }

    public int getTopo() {
        return topo;
    }

}
