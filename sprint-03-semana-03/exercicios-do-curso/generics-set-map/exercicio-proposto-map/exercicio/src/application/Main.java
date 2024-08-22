package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String path;

        System.out.println("Enter file full path: ");
        path = sc.next();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            Map<String, Integer> map = new HashMap<>();

            String line = br.readLine();
            while(line != null) {
                String[] fields = line.split(",");

                String name = fields[0];
                int votes = Integer.parseInt(fields[1]);

                if(map.containsKey(name)) {
                    int oldVotes = map.get(name);
                    map.put(name, oldVotes + votes);
                } else{
                    map.put(name, votes);
                }

                line = br.readLine();
            }

            for(String key : map.keySet()) {
                System.out.println(key + ": " + map.get(key));
            }

        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}