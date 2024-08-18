package application;

import java.util.Locale;
import java.util.Scanner;
import entities.Products;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Products product = new Products();

        int quantity;

        System.out.println("Enter product data: ");
        System.out.print("Name: ");
        product.name = sc.nextLine();

        System.out.print("Price: ");
        product.price = sc.nextDouble();

        System.out.print("Quantity in stock: ");
        product.quantity = sc.nextInt();

        System.out.println(product);

        System.out.print("\nEnter the number of products to be added in stock: ");
        quantity = sc.nextInt();

        product.AddProducts(quantity);

        System.out.println(product);

        System.out.print("\nEnter the number of products to be removed in stock: ");
        quantity = sc.nextInt();

        product.RemoveProducts(quantity);

        System.out.println(product);

        sc.close();
    }
}