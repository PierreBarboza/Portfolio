package org.example;

import java.util.Stack;

public class Aula {

    int[] v = {10, 50, 50, 10};

    public boolean ehPalindromo() {
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

    public void converteParaBinario(int num) {
        Stack<Integer> pilha = new Stack<Integer>();


        if(num == 0) {
            pilha.push(0);
        }


        while(num > 0) {
            int resto = num % 2;
            pilha.push(resto);
            num /= 2;
        }


        System.out.print("Valor em binário: ");
        while(!pilha.isEmpty()) {
            System.out.print(pilha.pop());
        }
    }
    }




