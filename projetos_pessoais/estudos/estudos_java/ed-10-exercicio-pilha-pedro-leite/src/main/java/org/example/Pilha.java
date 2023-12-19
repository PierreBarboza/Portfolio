package org.example;

public class Pilha {
    private int[] pilha;
    private int topo;

    public Pilha(int capacidade) {
        this.pilha = new int[capacidade];
        this.topo = -1;
    }

    public Boolean isEmpty() {
        if(topo == -1){
            return true;
        }
        return false;

    }
    public Boolean isFull() {

        if(topo == pilha.length -1){
            return true;
        }
        return false;
    }

    public void push(int info) {
        if(isFull()){
            throw new IllegalStateException("Pilha cheia!");
        }
            pilha[++topo] = info;


    }
    public int pop() {

        if(!isEmpty()){
           return pilha[topo--];

        }
        return -1;

    }
    public int peek() {
       if(isEmpty()){
           return -1;
       }

        return pilha[topo];
    }
    public void exibe() {
        if(isEmpty()){
            System.out.println("Pilha vazia");
        }
        if(isFull())
        {
            System.out.println("Pilha cheia");
        }
        for(int i = 0; i <= topo; i++){

            System.out.println(pilha[i]);
        }
    }
    public int getTopo() {
        return topo;
    }
}