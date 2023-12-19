package org.example;

public class Pilha {

    // 01) Atributos
    private int[] pilha; //vetor que representa a pilha
    private int topo; // contém o índice do topo da pilha

    // 02) Construtor
    public Pilha(int capacidade) {
        pilha = new int[capacidade];
        topo = -1;
    }

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        return topo == -1;
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    // 05) MÃ©todo push
    public void push(int info) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
            throw new IllegalStateException("Pilha cheia");
        }
        else {
//            topo++;
//            pilha[topo] = info;
            // as 2 instruções acima equivalem a esta abaixo:
            pilha[++topo] = info;
        }
    }

    // 06) MÃ©todo pop
    public int pop() {
        if (isEmpty()) {
            return -1;
        }
//        int retorno = pilha[topo];
//        topo--;
//        return retorno;

        return pilha[topo--];
    }

    // 07) MÃ©todo peek
    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return pilha[topo];
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        }
        else {
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}