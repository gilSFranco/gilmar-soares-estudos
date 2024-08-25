package application;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        list.stream()
                .forEach(System.out::println); //Method reference

        list.stream()
                .forEach(n -> System.out.println(n)); //Sem method reference

        //Metodos abstratos
        list.stream()
                .map(n -> multipliquePorDois(n))
                .forEach(System.out::println);

        list.stream()
                .map(Main::multipliquePorDois)
                .forEach(System.out::println);

        //Construtores
        list.stream()
                .map(n -> new BigDecimal(n))
                .forEach(System.out::println);

        list.stream()
                .map(BigDecimal::new)
                .forEach(System.out::println);

        //Varias instancias
        list.stream()
                .map(n -> n.doubleValue())
                .forEach(System.out::println);

        list.stream()
                .map(Integer::doubleValue)
                .forEach(System.out::println);

        //Varias instancias
        BigDecimal dois = new BigDecimal(2);
        
        list.stream()
                .map(BigDecimal::new)
                .map(b -> dois.multiply(b))
                .forEach(System.out::println);

        list.stream()
                .map(BigDecimal::new)
                .map(dois::multiply)
                .forEach(System.out::println);
    }

    public static Integer multipliquePorDois(Integer n) {
        return n * 2;
    }
}