package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //List
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        //forEach
        list.forEach(n -> System.out.println(n));

        //removeIf
        list.removeIf(n -> n % 2 == 0);
        list.forEach(n -> System.out.println(n));

        //replaceAll
        list.replaceAll(n -> n * 2);
        list.forEach(n -> System.out.println(n));

        //Map
        HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "Se");
        map.put(1, "inscreva");
        map.put(2, "no");
        map.put(3, "canal");

        map.forEach((k, v) -> System.out.println(k + " - " + v));

        //compute
        map.compute(1, (k, v) -> v + " agora");
        map.forEach((k, v) -> System.out.println(k + " - " + v));

        //merge
        map.merge(3, "!", (v1, v2) -> v1 + v2);
        map.forEach((k, v) -> System.out.println(k + " - " + v));

        
    }
}