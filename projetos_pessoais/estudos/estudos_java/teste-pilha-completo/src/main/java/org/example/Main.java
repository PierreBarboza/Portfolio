package org.example;

public class Main {
    public static void main(String[] args) {
        // Cria um objeto pilha
        Pilha pilha = new Pilha(5);

        // Empilha valores na pilha
        try {
            pilha.push(10);
            pilha.push(10);
            pilha.push(20);
            pilha.push(30);
            pilha.push(30);

        }
        catch (IllegalStateException erro) {
            System.out.println(erro);
        }
        // Exibe a pilha
        pilha.exibe();

        // Desempilhando um valor
        //System.out.println("Desempilhou: " + pilha.pop());

        // Exibe a pilha
        //pilha.exibe();

        // Testando o peek
        //System.out.println("Valor do topo: " + pilha.peek());
        //System.out.println("Valor do topo: " + pilha.peek());

        // Exibe a pilha
        //pilha.exibe();

        // Esvaziar a pilha, exibindo um por um
        /*
        System.out.println("\nEsvaziando a pilha:");
        while (!pilha.isEmpty()) {
            System.out.println("Desempilhou: " + pilha.pop());
        }

         */

    }

    public boolean ehPalindromo(Pilha pilha){

        Pilha pilha = new Pilha(v.length);


        for (int i = 0; i < v.length; i++) {

            pilha.push(v[i]);

        }
        for (int i = 0; i < v.length; i++) {
            if (pilha.pop() != v[i]) {
                System.out.println("Não é palindramo");
                return false;
            }
        }
        System.out.println("é palindramo");
        return true;

    }