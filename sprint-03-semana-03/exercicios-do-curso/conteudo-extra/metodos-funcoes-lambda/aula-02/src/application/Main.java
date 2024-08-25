package application;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 5, 8, 9);

        list.stream()
                .map((n) -> new StringBuilder().append(n).append("s"));
    }
}