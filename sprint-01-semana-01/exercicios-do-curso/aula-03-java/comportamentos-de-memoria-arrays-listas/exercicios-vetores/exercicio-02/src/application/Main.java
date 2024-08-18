package application;

import entities.Costumer;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int quantityRooms;

        System.out.print("How many rooms will be rented? ");
        quantityRooms = sc.nextInt();

        Costumer[] costumers = new Costumer[10];

        System.out.println();
        sc.nextLine();

        for(int i = 0; i < quantityRooms; i++) {
            String name, email;
            int room;

            System.out.printf("Rent #%d: %n", i + 1);
            System.out.print("Name: ");
            name = sc.nextLine();

            System.out.print("Email: ");
            email = sc.nextLine();

            System.out.print("Room: ");
            room = sc.nextInt();

            System.out.println();
            sc.nextLine();

            costumers[room] = new Costumer(name, email, room);
        }

        System.out.println("Busy rooms: ");

        for(int k = 0; k < costumers.length; k++) {
            if(costumers[k] != null){
                System.out.printf("%d: %s, %s.%n", costumers[k].getRoom(), costumers[k].getName(), costumers[k].getEmail());
            }
        }

        sc.close();
    }
}