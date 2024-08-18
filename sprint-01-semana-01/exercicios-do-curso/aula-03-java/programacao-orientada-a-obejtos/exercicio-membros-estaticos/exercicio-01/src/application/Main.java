package application;

import util.CurrencyConverter;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double price, dollars;

        System.out.print("What is the dollar price? ");
        price = sc.nextDouble();

        System.out.print("How many dollars will be bought? ");
        dollars = sc.nextDouble();

        System.out.printf("\nAmount to be paid in reais = %.2f.%n", CurrencyConverter.dollarsToReais(price, dollars));

        sc.close();
    }
}