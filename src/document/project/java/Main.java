package document.project.java;

import document.project.java.dictionary.Dictionary;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Dictionary<Integer, String> dictionary = new Dictionary<>();
        int key;
        String name;
        while (true){
            System.out.println("Insira um número inteiro: ");
            key = scan.nextInt();
            if (dictionary.containsKey(key)){
                System.out.println("Chave já existente.");
                System.out.println("Conteúdo vinculado à chave: " + dictionary.get(key));
            }else {
                System.out.println("Chave não encontrada.");
                System.out.println("Informe o conteúdo a ser salvo com a chave: ");
                name = scan.next();
                dictionary.put(name, key);
            }
            System.out.println(dictionary);
        }
    }
}

