package org.example;

public class PilhaObj<T> {
    private int topo;
    private T[] itens;

    public PilhaObj(int tamanho) {
        topo = -1;
        itens = (T[]) new Object[tamanho];
    }

    public void push(T elemento) {
        if(topo == itens.length - 1) {
            throw new RuntimeException("Pilha cheia");
        }
        topo++;
        itens[topo] = elemento;
    }

    public T pop() {
        if(topo == -1) {
            throw new RuntimeException("Pilha vazia");
        }
        T elemento = itens[topo];
        topo--;
        return elemento;
    }

    public boolean isEmpty() {
        return (topo == -1);
    }

    public boolean isFull() {
        return (topo == itens.length - 1);
    }

    public int size() {
        return (topo + 1);
    }
    public void inverterFrase(String frase){
        PilhaObj<Character> pilha = new PilhaObj<>(frase.length());


        for(int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            pilha.push(c);
        }

        System.out.print("\nFrase invertida: ");
        while(!pilha.isEmpty()) {
            System.out.print(pilha.pop());
        }
    }

    public void analisaCaractere(String frase){
        PilhaObj<Character> pilha = new PilhaObj<>(frase.length());


        for(int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                pilha.push(Character.toLowerCase(c));
            }
        }

        boolean palindromo = true;
        int i = 0;
        while(palindromo && !pilha.isEmpty()) {
            char c = pilha.pop();
            char c2 = Character.toLowerCase(frase.charAt(i));
            if(Character.isLetterOrDigit(c2)) {
                palindromo = (c == c2);
            }
            i++;
        }

        if(palindromo) {
            System.out.println("\nÉ palíndroma");
        } else {
            System.out.println("\nNão é palíndroma");
        }
    }
    }



