package application;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        String s = "Inscreva-se no canal e compartilhe esse video!";
        String[] split = s.split(" ");

        List<String> listStr = Arrays.asList(split);
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        //reduce soma
        Optional<Integer> soma = list.stream()
                .reduce((n1, n2) -> n1 + n2);

        System.out.println(soma.get());

        //reduce multplicacao
        Optional<Integer> multplicacao = list.stream()
                .reduce((n1, n2) -> n1 * n2);

        System.out.println(multplicacao.get());

        //reduce concatenacao
        Optional<String> concatenacao = listStr.stream()
                .reduce((s1, s2) -> s1.concat(s2));

        System.out.println(concatenacao.get());

        //reduce soma
        Integer soma2 = list.stream()
                .reduce(0, (n1, n2) -> n1 + n2);

        System.out.println(soma2);

        //reduce multplicacao
        Integer multplicacao2 = list.stream()
                .reduce(1, (n1, n2) -> n1 * n2);

        System.out.println(multplicacao2);

        //reduce concatenacao
        String concatenacao2 = listStr.stream()
                .reduce("", (s1, s2) -> s1.concat(s2));

        System.out.println(concatenacao2);

        //reduce - menor valor
        double menorValor = DoubleStream.of(1.5, 2.9, 6.7)
                .reduce(Double.POSITIVE_INFINITY, (d1, d2) -> Math.min(d1, d2));

        System.out.println(menorValor);

        //reduce soma
        Integer soma3 = list.stream()
                .reduce(0, (n1, n2) -> n1 + n2, (n1, n2) -> n1 + n2);

        System.out.println(soma3);

        //reduce map + combiner
        String reduce = list.stream()
                .reduce(
                        "",
                        (n1, n2) -> n1.toString().concat(n2.toString()),
                        (n1, n2) -> n1.concat(n2)
                );

        System.out.println(reduce);
    }
}