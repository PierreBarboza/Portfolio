package org.example;

public class Main {
    public static void main(String[] args) {


        Pilha pilha = new Pilha(5);
        pilha.push(1);
        pilha.push(2);
        pilha.push(7);
        pilha.push(8);
        pilha.exibe();
        Aula aula = new Aula();
        aula.ehPalindromo();
        aula.converteParaBinario(30);
        PilhaObj<Integer> pilhaObj = new PilhaObj<>(5);
        pilhaObj.push(1);
        pilhaObj.push(2);
        pilhaObj.inverterFrase("A pilha do gato");
        pilhaObj.analisaCaractere("Pincel");

    }
}