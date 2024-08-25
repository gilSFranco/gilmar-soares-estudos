package application;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(1, 1, 3, 4, 5, 6, 7, 7, 9, 1);

        lista.stream()
                .skip(1)
                .limit(10)
                .distinct()
                .filter(e -> e % 2 == 0)
                .map(e -> e * 2)
                .forEach(e -> System.out.println(e));

        long pares =  lista.stream()
                .filter(e -> e % 2 == 0)
                .count();

        Optional<Integer> result = lista.stream()
                //.filter(e -> e % 2 == 0)
                //.min(Comparator.naturalOrder());
                .max(Comparator.naturalOrder());

        List<Integer> novaLista = lista.stream()
                .skip(2)
                .map(x -> x * 2)
                .collect(Collectors.toList());

        Map<Boolean, List<Integer>> collect1 = lista.stream()
                .collect(Collectors.groupingBy(x -> x % 2 == 0));

        Map<Integer, List<Integer>> collect2 = lista.stream()
                .collect(Collectors.groupingBy(x -> x % 2));

        String csv = lista.parallelStream()
                .map(e -> String.valueOf(e))
                .collect(Collectors.joining(";"));

        System.out.println(csv);
    }
}