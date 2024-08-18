package application;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import enums.OrderStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String name, email;
        Date birthDate;
        int numberItems;
        OrderStatus status;

        System.out.println("Enter the client data: ");
        System.out.print("Name: ");
        name = sc.nextLine();

        System.out.print("Email: ");
        email = sc.nextLine();

        System.out.print("Birth date (DD/MM/YYYY): ");
        birthDate = sdf.parse(sc.next());

        Client client = new Client(name, email, birthDate);

        System.out.println("\nEnter order data: ");

        System.out.println();

        System.out.print("Status: ");
        status = OrderStatus.valueOf(sc.next());

        Order order = new Order(new Date(), status, client);

        System.out.print("\nHow many items to this order? ");
        numberItems = sc.nextInt();

        sc.nextLine();

        for(int i = 0; i < numberItems; i++) {
            String nameProduct;
            double price;
            int quantity;

            System.out.println("\nEnter #" + (i + 1) + " item data: ");
            System.out.print("Product name: ");
            nameProduct = sc.nextLine();

            System.out.print("Product price: ");
            price = sc.nextDouble();

            Product product = new Product(nameProduct, price);

            System.out.print("Product quantity: ");
            quantity = sc.nextInt();

            OrderItem orderItem = new OrderItem(quantity, price, product);

            order.addItem(orderItem);

            sc.nextLine();
        }

        System.out.println();

        System.out.println("ORDER SUMARY: ");
        System.out.println(order);

        sc.close();
    }
}