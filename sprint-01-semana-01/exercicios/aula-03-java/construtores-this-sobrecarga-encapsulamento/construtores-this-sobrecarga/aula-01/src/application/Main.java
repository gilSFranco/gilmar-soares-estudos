package application;

import java.util.Locale;
import java.util.Scanner;
import entities.Products;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String name;
        int quantity, changeInStock;
        double price;

        System.out.println("Enter product data: ");
        System.out.print("Name: ");
        name = sc.nextLine();

        System.out.print("Price: ");
        price = sc.nextDouble();

        //System.out.print("Quantity in stock: ");
        //quantity = sc.nextInt();

        Products product = new Products(name, price);

        System.out.println(product);

        System.out.print("\nEnter the number of products to be added in stock: ");
        changeInStock = sc.nextInt();

        product.AddProducts(changeInStock);

        System.out.println(product);

        System.out.print("\nEnter the number of products to be removed in stock: ");
        changeInStock = sc.nextInt();

        product.RemoveProducts(changeInStock);

        System.out.println(product);

        sc.close();
    }
}