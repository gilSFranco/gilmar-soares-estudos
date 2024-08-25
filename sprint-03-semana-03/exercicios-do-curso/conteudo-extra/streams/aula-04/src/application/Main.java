package application;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //Parenteses
        Runnable runnable = () -> System.out.println("Hello World");

        IntStream.range(0, 10)
                .filter((int n) -> n % 2 == 0)
                .reduce((n1, n2) -> n1 + n2)
                .ifPresent(System.out::println);

        //Chaves
        IntStream.range(0, 10)
                .filter((int n) -> {
                    System.out.println("Hello World");
                    return n % 2 == 0;
                })
                .forEach(n -> System.out.println(n));
    }
}