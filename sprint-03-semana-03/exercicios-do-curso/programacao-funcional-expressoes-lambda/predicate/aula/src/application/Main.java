package application;

import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        List<Product> list = new ArrayList<>();

        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        double min = 100.0;
        //Predicate<Product> pred = product -> product.getPrice() >= min;

        list.removeIf(product -> product.getPrice() >= min);

        for(Product p : list) {
            System.out.println(p);
        }
    }
}