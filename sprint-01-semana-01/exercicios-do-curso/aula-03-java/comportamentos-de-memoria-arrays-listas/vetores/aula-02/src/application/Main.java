package application;

import entities.Product;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int numero;
        double soma = 0, media;

        System.out.print("Digite um numero: ");
        numero = sc.nextInt();

        Product[] vect = new Product[numero];

        for(int i = 0; i < vect.length; i++) {
            String name;
            double price;

            sc.nextLine();
            System.out.print("Digite o nome do produto: ");
            name = sc.nextLine();

            System.out.print("Digite o preço: ");
            price = sc.nextDouble();

            vect[i] = new Product(name, price);
        }

        for(int k = 0; k < vect.length; k++) {
            soma += vect[k].getPrice();
        }

        media = soma / numero;

        System.out.printf("Average price: %.2f%n", media);

        sc.close();
    }
}