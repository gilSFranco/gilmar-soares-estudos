package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Collection
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().forEach(System.out::println);

        //Arrays
        Integer[] intArray = new Integer[] {1, 2, 3, 4, 5};
        Arrays.stream(intArray).forEach(System.out::println);

        //Stream.of
        Stream
                .of("Se", "inscreva", "no", "canal", "!")
                .forEach(System.out::println);

        //IntStream.range
        IntStream.range(0, 10).forEach(System.out::println);

        //Stream.iterate
        Stream
                .iterate(5, n -> n * 5)
                .limit(10)
                .forEach(System.out::println);

        //BufferedReader - lines
        //Stream.txt - 11, 12, 13
        File file = new File("streams.txt");

        try{
            FileReader in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);

            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Files
        Path p = Paths.get("");
        try {
            Files.list(p).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Random
        new Random()
                .ints()
                .limit(10)
                .forEach(System.out::println);

        String s = "Deixe um curtir no video!";
        Pattern pa = Pattern.compile(" ");
        pa.splitAsStream(s).forEach(System.out::println);
    }
}