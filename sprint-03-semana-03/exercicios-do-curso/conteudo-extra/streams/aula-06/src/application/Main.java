package application;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        //forEach x forEachOrdered
        list.parallelStream()
                .forEach(System.out::println);
        list.parallelStream()
                .forEachOrdered(System.out::println);

        //findAny
        list.parallelStream()
                .findAny()
                .ifPresent(System.out::println);

        list.stream()
                .findAny()
                .ifPresent(System.out::println);

        //unordered
        list.parallelStream()
                .skip(1)
                .limit(2)
                .forEach(System.out::println);

        //reduce
        //(1 + 2) + (3 + 4) = 10
        //(1 - 2) - (3 - 4) != -8
        list.parallelStream()
                .reduce((n1, n2) -> n1 + n2)
                .ifPresent(System.out::println);

        //collect
        Map<Integer, Boolean> map = list.parallelStream()
                .collect(
                        Collectors
                                .toConcurrentMap(n -> n, n -> n % 2 == 0)
                );

        System.out.println(map);

        //groupingBy
        Map<Boolean, List<Integer>> map2 = list.parallelStream()
                .collect(
                        Collectors
                                .groupingByConcurrent(n -> n % 2 == 0)
                );

        System.out.println(map2);
    }
}